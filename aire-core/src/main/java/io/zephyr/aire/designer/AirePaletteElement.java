package io.zephyr.aire.designer;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.internal.JsonSerializer;
import io.zephyr.aire.designer.model.ModelElement;
import lombok.Getter;
import lombok.Setter;

@Tag("aire-palette-element")
@CssImport("./styles/aire/components/designer/aire-palette-element.css")
@JsModule("application/aire/designer/elements/palette-element.ts")
public class AirePaletteElement extends Div {

  private ModelElement<?> element;

  public AirePaletteElement() {}

  public AirePaletteElement(ModelElement<?> element) {
    this.element = element;
    getElement().setPropertyJson("model", JsonSerializer.toJson(element));
  }
}
