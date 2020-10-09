package io.zephyr.aire.test.xpath;

import com.vaadin.flow.component.HasElement;
import lombok.val;

import java.util.Objects;

public class AttributeBinaryOperator extends AbstractElementMatcher {
  private final String value;
  private final String attribute;
  private final Operator operator;

  public AttributeBinaryOperator(Token token, Operator operator, String attribute, String value) {
    super(token);
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

  @Override
  public String toString() {
    return String.format(
        "Matcher[type=attribute-binary, operator='%s', attribute='%s', value='%s', token='%s'",
        operator, attribute, value, token);
  }
}
