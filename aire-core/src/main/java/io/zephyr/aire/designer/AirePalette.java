package io.zephyr.aire.designer;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.shared.Registration;

@Tag("aire-palette")
@JsModule("application/aire/designer/elements/palette.ts")
@CssImport("./styles/aire/components/designer/aire-palette.css")
public class AirePalette extends Div {

  public AirePalette() {
    for (int i = 0; i < 2; i++) {
      add(new AirePaletteElement());
    }
  }

  public Registration addAddElementListener(ComponentEventListener<AddElementEvent> listener) {
    return addListener(AddElementEvent.class, listener);
  }
}
