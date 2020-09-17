package io.zephyr.aire.test.xpath;

import com.vaadin.flow.component.HasElement;
import lombok.val;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ElementXPathMatcher {

  final PushbackReader reader;

  public ElementXPathMatcher(String expression) {
    this.reader = new PushbackReader(new StringReader(expression), 100);
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

      //TODO recurse
      throw new RuntimeException("Expected EOL, didn't get it");
    }
  }

  private boolean isDocumentSelection() throws IOException {

    int fst = reader.read();
    if (fst == '/') {
      int snd = reader.read();
      if (snd == '/') {
        return true;
      }
      reader.unread(snd);
    }
    reader.unread(fst);
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

  private boolean endOfExpression() throws IOException {
    int ch = reader.read();
    if (ch == -1 || ch == 65535) {
      return true;
    }
    reader.unread(ch);
    return false;
  }

  private ElementMatcher readElementMatcher() throws IOException {

    val ch = reader.read();
    reader.unread(ch);

    if (ch == '*') {
      return resolveWildcardMatcher();
    }
    if (Character.isLetterOrDigit(ch)) {
      return readNameElementMatcher();
    }
    throw new RuntimeException("Nope");
  }

  private ElementMatcher readNameElementMatcher() throws IOException {
    StringBuilder b = readIdentifier();
    val nameMatcher = new NameElementMatcher(b.toString());
    chomp();
    if (peek() == '[') {
      reader.read();
      val result = new CompositeElementMatcher();
      result.add(nameMatcher);
      readAttributeMatchers(result);
      int ch = reader.read();
      if (ch != ']') {
        throw new RuntimeException("Expected ']' to close attribute list");
      }
      return result;
    }
    return nameMatcher;
  }

  private ElementMatcher resolveWildcardMatcher() throws IOException {
    var matcher = new CompositeElementMatcher();
    matcher.add(new AllElementMatcher());
    reader.read();
    chomp();

    int ch = reader.read();

    if (ch == '[') {
      readAttributeMatchers(matcher);
    }

    ch = reader.read();
    if (ch != ']') {
      throw new RuntimeException("Expected ']' to close attribute list");
    }

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
      reader.read();
      if (peek() == '*') {
        reader.read();
        matcher.add(new AnyAttributeMatcher());
      } else {
        val attribute = readIdentifier().toString();
        val operator = readOperator();
        if (operator == null) {
          matcher.add(new AttributeExistenceElementMatcher(attribute));
        } else {
          matcher.add(new AttributeBinaryOperator(operator, attribute, readValue()));
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
      ch = reader.read();
      buffer.append((char) ch);
    } while (ch != ']');
    reader.unread(ch);
    return buffer.toString();
  }

  private String readStringLiteral() throws IOException {
    reader.read();
    val result = new StringBuilder();
    int ch;
    do {
      ch = reader.read();
      result.append((char) ch);
    } while (ch != '\'');
    result.deleteCharAt(result.length() - 1);
    return result.toString();
  }

  private Operator readOperator() throws IOException {
    chomp();

    int fst = reader.read();

    if (fst == ']') {
      reader.unread(fst);
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
    int result = reader.read();
    reader.unread(result);
    return result;
  }

  private void chomp() throws IOException {
    int ch;
    do {
      ch = reader.read();
    } while (Character.isWhitespace(ch));
    reader.unread(ch);
  }

  private StringBuilder readIdentifier() throws IOException {
    StringBuilder b = new StringBuilder();
    int ch;
    do {
      ch = reader.read();
      b.append((char) ch);
    } while (Character.isLetterOrDigit(ch));
    reader.unread(ch);
    b.deleteCharAt(b.length() - 1);
    return b;
  }

  private boolean isRoot() throws IOException {
    int ch = reader.read();
    if (ch == '/') {
      int sch = reader.read();
      if (sch != '/') {
        reader.unread(sch);
        return true;
      }
      reader.unread(sch);
    }
    reader.unread(ch);
    return false;
  }
}
