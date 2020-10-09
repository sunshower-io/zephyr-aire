package io.zephyr.aire.test.xpath;

import com.vaadin.flow.component.HasElement;

public class NameElementMatcher extends AbstractElementMatcher {
  final String name;

  public NameElementMatcher(Token token, String name) {
    super(token);
    this.name = name;
  }

  @Override
  public boolean matches(HasElement element) {
    return name.equals(element.getElement().getTag());
  }

  @Override
  public String toString() {
    return String.format("Matcher[type=element-name, name='%s', token='%s'", name, token);
  }
}
