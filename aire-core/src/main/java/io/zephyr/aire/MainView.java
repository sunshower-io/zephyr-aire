package io.zephyr.aire;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Section;
import com.vaadin.flow.router.*;
import io.zephyr.aire.api.Container;
import io.zephyr.aire.api.Location;
import io.zephyr.aire.components.*;
import io.zephyr.aire.designer.AddElementEvent;
import io.zephyr.aire.designer.AireDesigner;
import io.zephyr.aire.designer.AirePalette;
import io.zephyr.aire.elements.*;
import io.zephyr.aire.layout.AireApplicationViewport;
import com.vaadin.flow.component.dependency.CssImport;
import io.zephyr.kernel.core.Kernel;
import lombok.val;

import javax.inject.Inject;

@Route
@Location("home")
@Container(":main")
@CssImport("./styles/aire/layout/aire-structure.css")
@Uses(AireButton.class)
@Uses(AireCallToAction.class)
@Uses(AireFooter.class)
@Uses(AireHeader.class)
@Uses(AireIcon.class)
@Uses(AirePanel.class)
@Uses(AireButton.class)
@Uses(AireDrawer.class)
@Uses(AireFab.class)
@Uses(AireIconCard.class)
@Uses(AireMediaCard.class)
@Uses(AirePill.class)
@Uses(AireTabPane.class)
@Uses(AirePrimaryNavigation.class)
@Uses(AireSecondaryNavigation.class)
@Uses(AireAsideDrawerMenu.class)
public class MainView extends AireApplicationViewport {

  private final Kernel kernel;

  @Inject
  public MainView(Kernel kernel) {
    super();
    this.kernel = kernel;
    checkDefaults();
    addIcon();
  }

  private void addIcon() {
    val icon = new Image();
    icon.setSrc("icons/icon-dark.svg");

    val button = new Anchor();
    button.add(icon);
    getHeader().add(button);
  }

  private void checkDefaults() {

    val aside = new AireAsideDrawerMenu();

    val paletteContainer = new Section();

    val paletteButton = new Button();
    paletteButton.setIcon(AireIcon.icon("palette"));
    aside.add(paletteButton, paletteContainer);
    setSecondaryNavigation(aside);

    val palette = new AirePalette();
    paletteContainer.add(new Button("schnorp"));
    paletteContainer.add(palette);

    val designer = new AireDesigner();
    addContent(designer);
    palette.addAddElementListener(
        new ComponentEventListener<AddElementEvent>() {
          @Override
          public void onComponentEvent(AddElementEvent addElementEvent) {
            designer.addElement();
          }
        });
    //      val palette = new AireDesignerPalette();
    //
    //      palette.addEventListener()
    //    addContent(new AireDesigner());
    //    //    if (kernel.getModuleManager().getModules().size() == 1) { // aire's the only one
    // installed
    //    //      addContent(new UploadPluginCallToAction(kernel));
    //    //    }
  }
}
