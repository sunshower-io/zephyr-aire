package io.zephyr.aire.elements;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.dependency.CssImport;

@Tag("mark")
@CssImport("./styles/aire/components/aire-pill.css")
public class AirePill extends HtmlContainer implements ClickNotifier<AirePill> {

  public enum Variant {
    Primary,
    Secondary,
    Success,
    Danger,
    Warning,
    Info,
    Light,
    Dark
  }

  static final String CLASS_NAME = "aire-pill";

  public AirePill(String text, Variant variant) {
    setText(text);
    getClassNames().add(CLASS_NAME);
    getClassNames().add(variantFor(variant));
  }

  private static String variantFor(Variant variant) {
    return String.format("aire-" + variant.name().toLowerCase());
  }
}
