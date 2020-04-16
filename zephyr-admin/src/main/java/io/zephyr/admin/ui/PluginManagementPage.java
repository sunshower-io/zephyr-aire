package io.zephyr.admin.ui;

import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.RoutePrefix;
import io.zephyr.aire.components.AireTabPane;
import io.zephyr.aire.layout.AireApplicationViewport;
import org.springframework.stereotype.Component;

@Component
@RoutePrefix("plugins")
@ParentLayout(AireApplicationViewport.class)
public class PluginManagementPage extends AireTabPane {

  public PluginManagementPage() {
    configureTabs();
  }

  private void configureTabs() {
    addTab("Plugins", PluginListPage.class);
    addTab("Topology", PluginTopologyPage.class);
  }
}
