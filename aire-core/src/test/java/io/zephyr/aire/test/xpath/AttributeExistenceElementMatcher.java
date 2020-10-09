package io.zephyr.aire.test.xpath;

import com.vaadin.flow.component.HasElement;

public class AttributeExistenceElementMatcher extends AbstractElementMatcher {
  private final String attribute;

  public AttributeExistenceElementMatcher(Token token, String attribute) {
    super(token);
    this.attribute = attribute;
  }

  @Override
  public boolean matches(HasElement element) {
    return element.getElement().hasAttribute(attribute);
  }

  public String toString() {
    return String.format(
        "Matcher[type=attribute-exists, attribute='%s', token='%s']", attribute, token);
  }
}
