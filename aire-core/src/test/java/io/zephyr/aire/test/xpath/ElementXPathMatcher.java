package io.zephyr.aire.test.xpath;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;
import lombok.val;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

public class ElementXPathMatcher {

  private int regionEnd;
  private int regionStart;

  final PushbackReader reader;
  final StringBuilder program;

  public ElementXPathMatcher(String expression) {
    this.program = new StringBuilder();
    this.reader = new PushbackReader(new StringReader(expression), 100);
  }

  public HasElement select(HasElement root) {
    val result = match(root);
    if (result.isEmpty()) {
      return null;
    }
    return result.get(result.size() - 1);
  }

  public List<HasElement> match(HasElement root) {
    val results = new ArrayList<HasElement>();
    try {
      doMatch(results, root);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return results;
  }

  private void matchSilently(List<HasElement> results, HasElement current) {
    try {
      doMatch(results, current);
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }

  private void doMatch(List<HasElement> results, HasElement current) throws IOException {
    if (isRoot()) {
      results.clear(); // clear out match context
      selectFrom(results, current);
    } else if (isDocumentSelection()) {
      selectDocumentElements(results, current);
    } else if (isAttributeMatcher()) {
      selectMatchingElements(results);
    }
  }

  private void selectMatchingElements(List<HasElement> results) {}

  private boolean isAttributeMatcher() {
    return false;
  }

  private void selectDocumentElements(List<HasElement> results, HasElement current)
      throws IOException {
    val elementMatcher = readElementMatcher();

    val stack = new Stack<HasElement>();
    stack.push(current);

    while (!stack.isEmpty()) {
      val el = stack.pop();
      if (elementMatcher.matches(el)) {
        results.add(el);
      }
      el.getElement().getChildren().flatMap(t -> t.getComponent().stream()).forEach(stack::push);
    }

    if (!endOfExpression()) {
      if (!results.isEmpty()) {
        val subsequentMatchContext = results.get(results.size() - 1);
        subsequentMatchContext
            .getElement()
            .getChildren()
            .flatMap(t -> t.getComponent().stream())
            .forEach(t -> doMatchUnchecked(results, t));
      }
    }
  }

  private void doMatchUnchecked(List<HasElement> results, Component t) {
    try {
      doMatch(results, t);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private boolean isDocumentSelection() throws IOException {

    int fst = read();
    if (fst == '/') {
      int snd = read();
      if (snd == '/') {
        sync();
        return true;
      }
      unread(snd);
    }
    unread(fst);
    sync();
    return false;
  }

  private void selectFrom(List<HasElement> results, HasElement current) throws IOException {
    val elementMatcher = readElementMatcher();
    val eol = endOfExpression();
    if (eol && elementMatcher.matches(current)) {
      results.add(current);
    } else if (!eol) {
      current
          .getElement()
          .getChildren()
          .flatMap(child -> child.getComponent().stream())
          .forEach(child -> matchSilently(results, child));
    }
  }

  private String readAll() throws IOException {
    val b = new StringBuilder();
    for (int ch = 0; !(ch == -1 || ch == 65535); ch = reader.read()) {
      b.append((char) ch);
    }
    return b.toString();
  }

  private boolean endOfExpression() throws IOException {
    int ch = read();
    if (ch == -1 || ch == 65535) {
      return true;
    }
    unread(ch);
    sync();
    return false;
  }

  private ElementMatcher readElementMatcher() throws IOException {

    val ch = read();
    unread(ch);

    if (ch == '*') {
      return resolveWildcardMatcher();
    }
    if (Character.isLetterOrDigit(ch)) {
      return readNameElementMatcher();
    }
    sync();
    throw new RuntimeException("Nope");
  }

  private ElementMatcher readNameElementMatcher() throws IOException {
    StringBuilder b = readIdentifier();
    val nameMatcher =
        new NameElementMatcher(new Token(program, regionStart, regionEnd), b.toString());
    chomp();
    if (peek() == '[') {
      read();
      val result = new CompositeElementMatcher();
      result.add(nameMatcher);
      readAttributeMatchers(result);
      int ch = read();
      if (ch != ']') {
        throw new RuntimeException("Expected ']' to close attribute list");
      }
      return result;
    }
    return nameMatcher;
  }

  private ElementMatcher resolveWildcardMatcher() throws IOException {
    var matcher = new CompositeElementMatcher();
    matcher.add(new AllElementMatcher(new Token(program, regionStart, regionEnd)));
    read();
    chomp();

    int ch = read();

    if (ch == '[') {
      readAttributeMatchers(matcher);
    }

    ch = read();
    if (ch != ']') {
      throw new RuntimeException("Expected ']' to close attribute list");
    }
    sync();

    return matcher;
  }

  private void readAttributeMatchers(CompositeElementMatcher matcher) throws IOException {
    while (peek() != ']') {
      readAttributeMatcher(matcher);
    }
  }

  private void readAttributeMatcher(CompositeElementMatcher matcher) throws IOException {
    chomp();

    if (peek() == '@') {
      read();
      if (peek() == '*') {
        read();
        matcher.add(new AnyAttributeMatcher(new Token(program, regionStart, regionEnd)));
      } else {
        val attribute = readIdentifier().toString();
        val operator = readOperator();
        if (operator == null) {
          matcher.add(
              new AttributeExistenceElementMatcher(
                  new Token(program, regionStart, regionEnd), attribute));
        } else {
          matcher.add(
              new AttributeBinaryOperator(
                  new Token(program, regionStart, regionEnd), operator, attribute, readValue()));
        }
      }
    }
  }

  private String readValue() throws IOException {
    chomp();
    if (peek() == '\'') {
      return readStringLiteral();
    } else {
      return readLiteral();
    }
  }

  private String readLiteral() throws IOException {
    int ch;
    val buffer = new StringBuilder();
    do {
      ch = read();
      buffer.append((char) ch);
    } while (ch != ']');
    unread(ch);
    return buffer.toString();
  }

  private String readStringLiteral() throws IOException {
    read();
    val result = new StringBuilder();
    int ch;
    do {
      ch = read();
      result.append((char) ch);
    } while (ch != '\'');
    result.deleteCharAt(result.length() - 1);
    sync();
    return result.toString();
  }

  private Operator readOperator() throws IOException {
    chomp();

    int fst = read();

    if (fst == ']') {
      unread(fst);
      return null;
    }

    if (fst == '=') {
      return Operator.Equals;
    }

    if (fst == '!') {
      if (peek() == '=') {
        return Operator.NotEquals;
      }
    }

    throw new RuntimeException("Unsupported operator");
  }

  private int peek() throws IOException {
    int result = read();
    unread(result);
    return result;
  }

  private void chomp() throws IOException {
    int ch;
    do {
      ch = read();
    } while (Character.isWhitespace(ch));
    unread(ch);
    sync();
  }

  private StringBuilder readIdentifier() throws IOException {
    StringBuilder b = new StringBuilder();
    int ch;
    do {
      ch = read();
      b.append((char) ch);
    } while (isIdentifiedCharacter(ch));
    unread(ch);
    b.deleteCharAt(b.length() - 1);
    sync();
    return b;
  }

  private boolean isIdentifiedCharacter(int ch) {
    if (Character.isLetterOrDigit(ch)) {
      return true;
    }
    switch ((char) ch) {
      case '-':
      case '_':
        return true;
    }
    return false;
  }

  private boolean isRoot() throws IOException {
    int ch = read();
    if (ch == '/') {
      int sch = read();
      if (sch != '/') {
        unread(sch);
        return true;
      }
      unread(sch);
    }
    unread(ch);
    return false;
  }

  private int read() throws IOException {
    regionEnd++;
    int ch = reader.read();
    program.append((char) ch);
    return ch;
  }

  private void unread(int ch) throws IOException {
    program.deleteCharAt(regionEnd - 1);
    regionEnd--;
    reader.unread(ch);
  }

  private void sync() {
    regionStart = regionEnd;
  }
}
