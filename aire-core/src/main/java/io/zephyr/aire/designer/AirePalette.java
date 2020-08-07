package io.zephyr.aire.designer;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;

@Tag("aire-palette")
@JsModule("application/aire/palette.ts")
@CssImport("./styles/aire/components/aire-palette.css")
public class AirePalette extends Div {

  public AirePalette() {
      add(new Span("Whaddup"));
  }

}
