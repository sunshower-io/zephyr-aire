package io.zephyr.aire.test.xpath;

import com.vaadin.flow.component.HasElement;

public interface ElementMatcher {

  boolean matches(HasElement element);
}
