package io.zephyr.aire.designer;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import io.aire.designer.Images;
import io.aire.designer.Vertex;
import lombok.val;

@Tag("aire-palette")
@JsModule("application/aire/designer/elements/palette.ts")
@CssImport("./styles/aire/components/designer/aire-palette.css")
public class AirePalette extends Div {

  public AirePalette() {

    val el = new Vertex();
    val image = Images.palette("icons/icon-dark.svg");
    el.addImage(image);
    add(new AirePaletteElement(el));

    //    val el = new CellModelElement<>();
    //    el.setPaletteIcon("icons/icon-dark.svg");
    //
    //    add(new AirePaletteElement(el));
    //
    //    val el2 = new CellModelElement<>();
    //    el2.setPaletteIcon("icons/angellist.svg");
    //    add(new AirePaletteElement(el2));
  }
}
