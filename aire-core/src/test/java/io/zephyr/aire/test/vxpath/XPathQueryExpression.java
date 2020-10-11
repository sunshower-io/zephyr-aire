package io.zephyr.aire.test.vxpath;

import com.vaadin.flow.component.HasElement;
import lombok.val;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class XPathQueryExpression implements QueryExpression {

  /**
   * I can't find how far ahead XPath actually needs to look ahead. It is LL(K) parseable, and K=100
   * seems to be overkill
   */
  private static final int MAX_LOOKAHEAD = 100;

  private final PushbackReader reader;
  private int end;
  private int start;

  public XPathQueryExpression(String query) {
    this.reader = new PushbackReader(new StringReader(query), MAX_LOOKAHEAD);
  }

  @Override
  public List<HasElement> select(HasElement root) {
    val results = new ArrayList<HasElement>();
    try {
      doSelect(results, root);
    } catch (IOException ex) {
      throw new QueryException(ex);
    }
    return results;
  }

  private void doSelect(ArrayList<HasElement> results, HasElement root) throws IOException {
    val selector = expectSelector();
    switch (selector.getTokenType()) {
        case DocumentSelector:

    }
  }

  private Selector expectSelector() throws IOException {
    consumePrecedingWhitespace();
    if (peek() == '/') {
      return rootOrDocumentSelector();
    }
    return null;
  }

  private Selector rootOrDocumentSelector() throws IOException {
    read();
    if (peek() == '/') {
      return documentSelector();
    }
    return rootSelector();
  }

  private Selector rootSelector() {
    return new DocumentSelector();
  }

  private Selector documentSelector() {
    return null;
  }

  private int read() throws IOException {
    end++;
    return reader.read();
  }

  private char peek() throws IOException {
    int ch = reader.read();
    reader.unread(ch);
    return (char) ch;
  }

  private void consumePrecedingWhitespace() throws IOException {
    int ch;
    do {
      ch = read();
    } while (Character.isWhitespace(ch));
    unread(ch);
  }

  private void unread(int ch) throws IOException {
    end--;
    reader.unread(ch);
  }
}
