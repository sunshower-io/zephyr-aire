package io.zephyr.aire.elements;

import com.vaadin.flow.component.HasStyle;

public final class AireUtilities {

    private AireUtilities() {
        throw new IllegalStateException();
    }

    static void toggleClass(HasStyle element, String className) {
        if (element.hasClassName(className)) {
            element.removeClassName(className);
        } else {
            element.addClassName(className);
        }
    }
}
