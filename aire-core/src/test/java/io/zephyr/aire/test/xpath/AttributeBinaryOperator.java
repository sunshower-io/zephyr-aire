package io.zephyr.aire.test.xpath;

import com.vaadin.flow.component.HasElement;
import lombok.val;

import java.util.Objects;

public class AttributeBinaryOperator implements ElementMatcher {
  private final String value;
  private final String attribute;
  private final Operator operator;

  public AttributeBinaryOperator(Operator operator, String attribute, String value) {
    this.operator = operator;
    this.attribute = attribute;
    this.value = value;
  }

  @Override
  public boolean matches(HasElement element) {
    val el = element.getElement();
    if (operator == Operator.NotEquals) {
      return !Objects.equals(el.getAttribute(attribute), value);
    }
    return Objects.equals(el.getAttribute(attribute), value);
  }
}
