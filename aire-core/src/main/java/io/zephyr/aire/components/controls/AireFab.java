package io.zephyr.aire.components.controls;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import io.zephyr.aire.api.Positionable;
import io.zephyr.aire.components.AireSize;
import io.zephyr.aire.components.AireVariant;
import io.zephyr.aire.components.HasVariant;

@Tag("aire-fab")
@JsModule("lit-element")
@JsModule("./components/aire/aire-fab.ts")
@CssImport("./styles/aire/components/aire-fab.css")
public class AireFab extends HtmlContainer
    implements HasVariant,
        ClickNotifier<AireFab>,
        Positionable.TopPositionable<AireFab>,
        Positionable.BottomPositionable<AireFab>,
        Positionable.LeftPositionable<AireFab>,
        Positionable.RightPositionable<AireFab> {

  private AireSize    size;
  private AireVariant current;

  public AireFab() {
    this(AireSize.Large);
  }

  public AireFab(AireSize size) {
    this(AireVariant.Primary, size);
  }

  public AireFab(AireVariant variant, AireSize size) {
    this(null, variant, size);
  }

  public AireFab(Component icon, AireVariant variant, AireSize size) {
    setSize(size);
    setVariant(variant);
    if (icon != null) {
      add(icon);
    }
    bottom().right();
  }

  public void setSize(AireSize size) {
    if (this.size != null) {
      this.size.clear(this);
    }
    size.set(this);
    this.size = size;
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
