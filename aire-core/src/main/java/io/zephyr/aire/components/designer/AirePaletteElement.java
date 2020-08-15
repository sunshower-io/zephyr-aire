package io.zephyr.aire.components.designer;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.internal.JsonSerializer;
import io.aire.designer.Element;

@Tag("aire-palette-element")
@CssImport("./styles/aire/components/designer/aire-palette-element.css")
@JsModule("application/aire/designer/elements/palette-element.ts")
public class AirePaletteElement extends Div {

  final Element element;

  public AirePaletteElement(Element element) {
    this.element = element;
    getElement().setPropertyJson("model", JsonSerializer.toJson(element));
  }
}
