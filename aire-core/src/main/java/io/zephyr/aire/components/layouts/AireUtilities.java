package io.zephyr.aire.components.layouts;

import com.vaadin.flow.component.HasStyle;

public final class AireUtilities {

  private AireUtilities() {
    throw new IllegalStateException();
  }

  public static void toggleClass(HasStyle element, String className) {
    if (element.hasClassName(className)) {
      element.removeClassName(className);
    } else {
      element.addClassName(className);
    }
  }
}
