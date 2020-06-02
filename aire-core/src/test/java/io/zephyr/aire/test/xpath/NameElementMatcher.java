package io.zephyr.aire.test.xpath;

import com.vaadin.flow.component.HasElement;

public class NameElementMatcher implements ElementMatcher {
  final String name;

  public NameElementMatcher(String name) {
    this.name = name;
  }

  @Override
  public boolean matches(HasElement element) {
    return name.equals(element.getElement().getTag());
  }
}
