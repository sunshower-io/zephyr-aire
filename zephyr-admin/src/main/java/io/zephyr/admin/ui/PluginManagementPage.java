package io.zephyr.admin.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.RoutePrefix;
import com.vaadin.flow.router.RouterLayout;
import io.zephyr.aire.api.Decorate;
import io.zephyr.aire.components.AireAsideDrawerMenu;
import io.zephyr.aire.components.AireTabPane;
import io.zephyr.aire.layout.AireApplicationViewport;
import lombok.val;
import org.springframework.stereotype.Component;

@Component
@PageTitle("Plugins")
@RoutePrefix("plugins")
@ParentLayout(AireApplicationViewport.class)
public class PluginManagementPage extends AireTabPane implements RouterLayout {

  private AireAsideDrawerMenu drawer;
  private AireApplicationViewport parent;

  public PluginManagementPage() {
    configureTabs();
    drawer = new AireAsideDrawerMenu();
  }

  private void configureTabs() {
    addTab("Plugins", PluginListPage.class);
    addTab("Topology", PluginTopologyPage.class);
  }

  @Decorate
  public void decorate(AireApplicationViewport viewport) {
    val instance = new AireAsideDrawerMenu();
    val btn = new Button();
    btn.setIcon(new Icon(VaadinIcon.ACADEMY_CAP));
    instance.add(btn, PluginTopologyPage.class);
    viewport.setSecondaryNavigation(instance);
  }
}
