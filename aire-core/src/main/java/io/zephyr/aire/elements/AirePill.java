package io.zephyr.aire.elements;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.dependency.CssImport;
import io.aire.core.AireComponent;

@Tag("mark")
@CssImport("./styles/aire/components/aire-pill.css")
public class AirePill extends HtmlContainer implements ClickNotifier<AirePill>, AireComponent {
  static final String CLASS_NAME = "aire-pill";

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

  private String current;

  public AirePill(String text, Variant variant) {
    setText(text);
    getClassNames().add(CLASS_NAME);
    setVariant(variant);
  }

  public void setVariant(Variant variant) {
    if (current != null) {
      getClassNames().remove(current);
    }
    getClassNames().add(current = variantFor(variant));
  }

  private static String variantFor(Variant variant) {
    return String.format("aire-" + variant.name().toLowerCase());
  }
}
