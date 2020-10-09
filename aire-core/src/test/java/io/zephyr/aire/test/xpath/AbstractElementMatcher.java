package io.zephyr.aire.test.xpath;

public abstract class AbstractElementMatcher implements ElementMatcher {
  final Token token;

  protected AbstractElementMatcher(Token token) {
    this.token = token;
  }

  @Override
  public Token getToken() {
    return token;
  }
}
