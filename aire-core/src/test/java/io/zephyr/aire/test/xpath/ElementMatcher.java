package io.zephyr.aire.test.xpath;

import com.vaadin.flow.component.HasElement;

public interface ElementMatcher {

  Token getToken();

  boolean matches(HasElement element);
}
