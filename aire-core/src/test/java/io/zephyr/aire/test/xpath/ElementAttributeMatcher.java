package io.zephyr.aire.test.xpath;

import com.vaadin.flow.component.HasElement;
import io.sunshower.gyre.Pair;
import lombok.val;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ElementAttributeMatcher implements ElementMatcher {

  final ElementMatcher tagMatcher;
  final Map<String, ElementMatcher> attributes;

  public ElementAttributeMatcher(
      ElementMatcher tagMatcher, Set<Pair<String, ElementMatcher>> attributes) {
    this.tagMatcher = tagMatcher;
    this.attributes = new HashMap<>();
    for (val attr : attributes) {
      this.attributes.put(attr.fst, attr.snd);
    }
  }

  @Override
  public boolean matches(HasElement element) {
    return tagMatcher.matches(element)
        && element
            .getElement()
            .getAttributeNames()
            .allMatch(t -> attributes.containsKey(t) && attributes.get(t).matches(element));
  }
}
