package io.zephyr.aire.test.vxpath;

import com.vaadin.flow.component.HasElement;

public class DocumentSelector implements Selector {
    @Override
    public TokenType getTokenType() {
    return null;
    }

    @Override
    public boolean matches(HasElement element) {
        return false;
    }
}
