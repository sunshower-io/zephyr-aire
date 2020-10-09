package io.zephyr.aire.test.xpath;

import com.vaadin.flow.component.HasElement;

public class BinaryOperator implements ElementMatcher {
    @Override
    public Token getToken() {
    return null;
    }

    @Override
    public boolean matches(HasElement element) {
    return false;
    }
}
