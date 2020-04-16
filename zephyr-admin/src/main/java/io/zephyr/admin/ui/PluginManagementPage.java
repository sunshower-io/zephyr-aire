package io.zephyr.admin.ui;

import com.vaadin.flow.component.html.Article;
import com.vaadin.flow.component.html.Section;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import io.zephyr.aire.layout.AireApplicationViewport;
import lombok.val;
import org.springframework.stereotype.Component;

@Component
@Route(value = "plugins", layout = AireApplicationViewport.class)
public class PluginManagementPage extends TabLayout {

  public PluginManagementPage() {
    configureTabs();
  }

  private void configureTabs() {
    val pluginList = new Tab("Plugins");
    val topologyView = new Tab("Plugin Topology");
    add(pluginList, topologyView);
  }
}
