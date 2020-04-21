package io.zephyr.admin.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import io.zephyr.aire.api.Decorate;
import io.zephyr.aire.api.Undecorate;
import io.zephyr.aire.components.AireAsideDrawerMenu;
import io.zephyr.aire.layout.AireApplicationViewport;
import io.zephyr.kernel.core.Kernel;
import lombok.val;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Decorate
@Undecorate
@Component
@Route(value = "list", layout = PluginManagementPage.class)
public class PluginListPage extends VerticalLayout {

  private AireAsideDrawerMenu instance;

  @Inject
  public PluginListPage(final Kernel kernel) {
    this.setPadding(true);
    val plugins = kernel.getModuleManager().getModules();
    for (val plugin : plugins) {
      add(new ModuleCard(plugin));
    }
  }

  @Decorate
  public void decorate(AireApplicationViewport viewport) {
    instance = new AireAsideDrawerMenu();
    var btn = new Button();
    btn.setIcon(new Icon(VaadinIcon.ACADEMY_CAP));
    instance.add(btn, PluginTopologyPage.class);
    btn = new Button();
    btn.setIcon(new Icon(VaadinIcon.INVOICE));
    instance.add(btn, PluginTopologyPage.class);
    viewport.setSecondaryNavigation(instance);

    btn = new Button();
    btn.setIcon(new Icon(VaadinIcon.INVOICE));
    instance.add(btn, PluginTopologyPage.class);
    viewport.setSecondaryNavigation(instance);

    btn = new Button();
    btn.setIcon(new Icon(VaadinIcon.INVOICE));
    instance.add(btn, PluginTopologyPage.class);
    viewport.setSecondaryNavigation(instance);

    btn = new Button();
    btn.setIcon(new Icon(VaadinIcon.INVOICE));
    instance.add(btn, PluginTopologyPage.class);
    viewport.setSecondaryNavigation(instance);
  }

  @Undecorate
  public void undecorate(AireApplicationViewport viewport) {
    viewport.setSecondaryNavigation(null);
  }
}
