package io.zephyr.aire.test.xpath;

import com.vaadin.flow.component.HasElement;

public class AllElementMatcher implements ElementMatcher {
  @Override
  public boolean matches(HasElement element) {
    return true;
  }
}
