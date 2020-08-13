package io.zephyr.aire.designer;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import io.aire.designer.Images;
import io.aire.designer.Vertex;

@Tag("aire-palette")
@JsModule("application/aire/designer/elements/palette.ts")
@CssImport("./styles/aire/components/designer/aire-palette.css")
public class AirePalette extends Div {

  public AirePalette() {

    addElement("icons/icon-dark.svg", "icons/angellist.svg");
    addElement("icons/angellist.svg", "icons/icon-dark.svg");
  }

  private void addElement(String paletteIcon, String elementIcon) {
    var el = new Vertex();
    var image = Images.palette(paletteIcon);
    el.addImage(image);

    image = Images.element(elementIcon);
    el.addImage(image);
    add(new AirePaletteElement(el));
  }
}
