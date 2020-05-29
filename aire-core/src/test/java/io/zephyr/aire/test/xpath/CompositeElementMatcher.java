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
  public boolean matches(HasElement element) {
    boolean result = true;
    for (val matcher : matchers) {
      result = matcher.matches(element);
      if (!result) {
        return false;
      }
    }
    return true;
    //    return matchers.stream().allMatch(t -> t.matches(element));
  }

  public void add(ElementMatcher matcher) {
    matchers.add(matcher);
  }
}
