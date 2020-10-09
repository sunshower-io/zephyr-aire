package io.zephyr.aire.test.xpath;

import com.vaadin.flow.component.HasElement;
import lombok.val;

import java.util.ArrayList;
import java.util.List;

public class CompositeElementMatcher implements ElementMatcher {

  final List<ElementMatcher> matchers;

  public CompositeElementMatcher() {
    this.matchers = new ArrayList<>();
  }

  @Override
  public Token getToken() {
    return null;
  }

  @Override
  public boolean matches(HasElement element) {
    boolean result;
    for (val matcher : matchers) {
      result = matcher.matches(element);
      if (!result) {
        return false;
      }
    }
    return true;
  }

  public void add(ElementMatcher matcher) {
    matchers.add(matcher);
  }

  @Override
  public String toString() {
    val result = new StringBuilder();
    result.append("Matcher[type=composite, values={\n");
    for (val matcher : matchers) {
      result.append("\t").append(matcher.toString()).append("\n");
    }
    result.append("}]");
    return result.toString();
  }
}
