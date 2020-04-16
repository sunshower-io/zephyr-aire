package io.zephyr.aire.elements;

import com.vaadin.flow.component.ClickNotifier;
import com.vaadin.flow.component.HtmlContainer;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import lombok.val;

@Tag("i")
@JsModule("./icons/js/all.js")
public class AireIcon extends HtmlContainer implements ClickNotifier<AireIcon> {

  AireIcon(String iconName) {
    this.addClassName("fas");
    this.addClassName("fa-" + parseName(iconName));
  }

  static String parseName(String name) {
    final StringBuilder b = new StringBuilder();
    for (int i = 0; i < name.length(); i++) {
      val ch = name.charAt(i);
      if (i > 1) {
        if (Character.isUpperCase(ch)) {
          b.append("-").append(Character.toLowerCase(ch));
          continue;
        }
      }
      b.append(Character.toLowerCase(ch));
    }
    return b.toString();
  }

  public static AireIcon icon(String type) {
    return new AireIcon(type);
  }
}
