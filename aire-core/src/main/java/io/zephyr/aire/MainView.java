package io.zephyr.aire;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.*;
import io.zephyr.aire.api.Location;
import io.zephyr.aire.components.AireAsideDrawerMenu;
import io.zephyr.aire.components.AireFab;
import io.zephyr.aire.elements.AireIcon;
import io.zephyr.aire.layout.AireApplicationViewport;
import com.vaadin.flow.component.dependency.CssImport;
import lombok.val;

@Route
@Location("home")
@CssImport("./styles/aire/layout/aire-structure.css")
public class MainView extends AireApplicationViewport {

  public MainView() {
    val drawer = new AireAsideDrawerMenu();
    val fst = new Button();
    fst.setIcon(new Icon(VaadinIcon.INVOICE));
    drawer.add(fst, new Test());

    val snd = new Button();
    snd.setIcon(new Icon(VaadinIcon.AIRPLANE));
    drawer.add(snd, new Button("frapper"));
    setSecondaryNavigation(drawer);
    val fab = new AireFab();
    fab.add(AireIcon.icon("plus"));
    addContent(fab);
//    addContent(new AireFab());
  }
}
