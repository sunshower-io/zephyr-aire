package io.zephyr.aire.test.xpath;

import com.vaadin.flow.component.HasElement;
import lombok.val;

public class AnyAttributeMatcher implements ElementMatcher {
  @Override
  public boolean matches(HasElement element) {
    return element.getElement().getAttributeNames().findAny().isPresent();
  }
}
