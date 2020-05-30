package io.zephyr.aire;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.router.*;
import io.zephyr.aire.api.Container;
import io.zephyr.aire.api.Location;
import io.zephyr.aire.elements.AireCallToAction;
import io.zephyr.aire.layout.AireApplicationViewport;
import com.vaadin.flow.component.dependency.CssImport;
import io.zephyr.aire.views.UploadPluginCallToAction;
import io.zephyr.kernel.core.Kernel;
import lombok.val;

import javax.inject.Inject;

@Route
@Location("home")
@Container(":main")
@CssImport("./styles/aire/layout/aire-structure.css")
public class MainView extends AireApplicationViewport {

  private final Kernel kernel;

  @Inject
  public MainView(Kernel kernel) {
    this.kernel = kernel;
    checkDefaults();
    addIcon();

    //    val drawer = new AireAsideDrawerMenu();
    //    val fst = new Button();
    //    fst.setIcon(new Icon(VaadinIcon.INVOICE));
    //    drawer.add(fst, new Test());
    //
    //    val snd = new Button();
    //    snd.setIcon(new Icon(VaadinIcon.AIRPLANE));
    //    drawer.add(snd, new Button("frapper"));
    //    setSecondaryNavigation(drawer);
    //    val fab = new AireFab();
    //    fab.add(AireIcon.icon("plus"));
    //    addContent(fab);
    ////    addContent(new AireFab());
  }

  private void addIcon() {
    val icon = new Image();
    icon.setSrc("icons/icon-dark.svg");

    val button = new Anchor();
    button.add(icon);
    getHeader().add(button);
  }

  private void checkDefaults() {
    if (kernel.getModuleManager().getModules().size() == 1) { // aire's the only one installed
      addContent(new UploadPluginCallToAction(kernel));
    }
  }
}
