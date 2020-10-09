package io.zephyr.aire.test.xpath;

import com.vaadin.flow.component.HasElement;
import lombok.val;

public class AnyAttributeMatcher extends AbstractElementMatcher {
  public AnyAttributeMatcher(Token token) {
    super(token);
  }

  @Override
  public boolean matches(HasElement element) {
    return element.getElement().getAttributeNames().findAny().isPresent();
  }

  @Override
  public String toString() {
    return "Matcher[type=any-attribute, syntax='[@]'";
  }
}
