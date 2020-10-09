package io.zephyr.aire.test.xpath;

import com.vaadin.flow.component.HasElement;

public class AllElementMatcher extends AbstractElementMatcher {
  public AllElementMatcher(Token token) {
    super(token);
  }

  @Override
  public boolean matches(HasElement element) {
    return true;
  }

  @Override
  public String toString() {
    return String.format("Matcher[type=accept-all, syntax='*', token='%s']", token);
  }
}
