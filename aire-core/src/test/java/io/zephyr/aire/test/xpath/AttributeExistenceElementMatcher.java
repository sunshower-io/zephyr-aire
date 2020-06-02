package io.zephyr.aire.test.xpath;

import com.vaadin.flow.component.HasElement;

public class AttributeExistenceElementMatcher implements ElementMatcher {
  private final String attribute;

  public AttributeExistenceElementMatcher(String attribute) {
    this.attribute = attribute;
  }

  @Override
  public boolean matches(HasElement element) {
    return element.getElement().hasAttribute(attribute);
  }
}
