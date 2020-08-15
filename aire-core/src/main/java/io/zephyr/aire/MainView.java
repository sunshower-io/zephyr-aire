package io.zephyr.aire;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.*;
import io.zephyr.aire.api.Container;
import io.zephyr.aire.api.Location;
import io.zephyr.aire.components.*;
import io.zephyr.aire.designer.AireDesigner;
import io.zephyr.aire.designer.AirePalette;
import io.zephyr.aire.elements.*;
import io.zephyr.aire.layout.AireApplicationViewport;
import com.vaadin.flow.component.dependency.CssImport;
import io.zephyr.kernel.core.Kernel;
import lombok.val;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Route
@PageTitle("Zephyr - Home")
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
@Uses(AireToolbar.class)
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

  public static class TestDesigner extends AbstractAireContainer<Article> {
    public TestDesigner() {
      getElement().getClassList().add("expand");

      val designer = new AireDesigner();
      add(designer);
    }
  }

  private void checkDefaults() {

    val tabPanel = new AireTabPane();

    tabPanel.setTabPlacement(AireTabPane.TabPanelPlacement.BOTTOM);
    val button = new Button(new Icon(VaadinIcon.PLUS));
    val buttonTab = new AireTab(AireTab.TabPlacement.END, button);
    val count = new AtomicInteger();
    button.addClickListener(
        (ComponentEventListener<ClickEvent<Button>>)
            buttonClickEvent -> {
              val id = "Test " + count.incrementAndGet();
              val toolbar = new AireToolbar();
              val addGridButton = new AireToggleButton(AireIcon.icon("border-all"));
              val setConnectableButton = new AireToggleButton(AireIcon.icon("arrows-alt-v"));
              val designer = new AireDesigner(id);
              setConnectableButton.addClickListener(
                  click -> designer.setConnectable(!designer.isConnectable()));

              addGridButton.addClickListener(
                  click -> {
                    designer.addGrid();
                  });
              toolbar.add(setConnectableButton);
              toolbar.add(addGridButton);

              val div = new Div();
              div.getClassNames().addAll(Set.of("expand", "flex"));
              div.add(toolbar);
              val designerParent = new Div();
              designerParent.getClassNames().addAll(Set.of("expand", "flex"));
              designerParent.add(designer);
              div.add(designerParent);

              val tab = tabPanel.addTab(id, () -> div);
              tabPanel.activate(tab);
            });

    tabPanel.addTab(buttonTab);

    val aside = new AireAsideDrawerMenu();

    val paletteContainer = new Section();

    val paletteButton = new Button();
    paletteButton.setIcon(AireIcon.icon("palette"));
    aside.add(paletteButton, paletteContainer);
    setSecondaryNavigation(aside);

    val palette = new AirePalette();
    paletteContainer.add(palette);

    addContent(tabPanel);

    //
    //      palette.addEventListener()
    //    addContent(new AireDesigner());
    //    //    if (kernel.getModuleManager().getModules().size() == 1) { // aire's the only one
    // installed
    //    //      addContent(new UploadPluginCallToAction(kernel));
    //    //    }
  }
}
