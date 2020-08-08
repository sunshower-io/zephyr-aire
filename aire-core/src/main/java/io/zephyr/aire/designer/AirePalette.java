package io.zephyr.aire.designer;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.shared.Registration;
import io.zephyr.aire.designer.model.CellModelElement;
import lombok.val;

@Tag("aire-palette")
@JsModule("application/aire/designer/elements/palette.ts")
@CssImport("./styles/aire/components/designer/aire-palette.css")
public class AirePalette extends Div {

  public AirePalette() {
    val el = new CellModelElement<>();
    el.setPaletteIcon("icons/icon-dark.svg");

    add(new AirePaletteElement(el));

    val el2 = new CellModelElement<>();
    el2.setPaletteIcon("icons/angellist.svg");
    add(new AirePaletteElement(el2));
  }
}
