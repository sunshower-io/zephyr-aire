package io.zephyr.aire.components.controls;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.dependency.CssImport;
import io.aire.core.AireComponent;
import io.zephyr.aire.components.AireVariant;
import io.zephyr.aire.components.HasVariant;

@Tag("mark")
@CssImport("./styles/aire/components/aire-pill.css")
public class AirePill extends HtmlContainer
    implements ClickNotifier<AirePill>, AireComponent, HasVariant {
  static final String CLASS_NAME = "aire-pill";

  private AireVariant current;

  public AirePill(String text, AireVariant variant) {
    setText(text);
    getClassNames().add(CLASS_NAME);
    variant.set(this);
  }

  @Override
  public AireVariant current() {
    return current;
  }

  @Override
  public void setCurrent(AireVariant variant) {
    this.current = variant;
  }
}
