package io.zephyr.admin.ui;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import io.zephyr.aire.MainView;
import io.zephyr.aire.api.Decorate;
import io.zephyr.aire.api.Undecorate;
import io.zephyr.aire.components.AireAsideDrawerMenu;
import io.zephyr.aire.components.AireFab;
import io.zephyr.aire.elements.AireIcon;
import io.zephyr.aire.layout.AireApplicationViewport;
import io.zephyr.kernel.Module;
import io.zephyr.kernel.core.Kernel;
import lombok.val;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Decorate
@Undecorate
@Component
@Route(value = "list", layout = PluginManagementPage.class)
public class PluginListPage extends HorizontalLayout {

  private final Kernel kernel;
  private Button infoButton;
  private AireAsideDrawerMenu instance;

  @Inject
  public PluginListPage(final Kernel kernel) {
    this.kernel = kernel;
    this.setPadding(true);
    val plugins = kernel.getModuleManager().getModules();
    for (val plugin : plugins) {
      val card = new ModuleCard(plugin);
      card.addClickListener(new CardClickListener(plugin));
      add(card);
    }

    add(createUploadPluginsFab());
  }

  @Decorate
  public void decorate(MainView viewport) {
    instance = new AireAsideDrawerMenu();

    infoButton = new Button();
    infoButton.setIcon(AireIcon.icon("info-circle"));
    instance.add(infoButton, new Div());
    viewport.setSecondaryNavigation(instance);
  }

  @Undecorate
  public void undecorate(MainView viewport) {
    viewport.setSecondaryNavigation(null);
  }

  private AireFab createUploadPluginsFab() {
    val fab = new AireFab();
    fab.add(AireIcon.icon("plus"));
    fab.addClickListener(
        (ComponentEventListener<ClickEvent<AireFab>>)
            aireFabClickEvent -> {
              val dialog = new UploadModuleDialog(kernel);
              dialog.open();
            });

    return fab;
  }

  final class CardClickListener implements ComponentEventListener<ClickEvent<ModuleCard>> {

    final Module module;

    CardClickListener(Module module) {
      this.module = module;
    }

    @Override
    public void onComponentEvent(ClickEvent<ModuleCard> evt) {
      instance.setContent(infoButton, new ModuleInfoPane(kernel, module));
    }
  }
}
