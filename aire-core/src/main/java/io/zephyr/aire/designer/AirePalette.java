package io.zephyr.aire.designer;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.shared.Registration;

@Tag("aire-palette")
@JsModule("application/aire/designer/elements/palette.ts")
@CssImport("./styles/aire/components/aire-palette.css")
public class AirePalette extends Div {

  public AirePalette() {
    add(new Span("Whaddup"));
  }

  public Registration addAddElementListener(ComponentEventListener<AddElementEvent> listener) {
    return addListener(AddElementEvent.class, listener);
  }
}
