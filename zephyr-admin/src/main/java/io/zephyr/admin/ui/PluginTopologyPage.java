package io.zephyr.admin.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Article;
import com.vaadin.flow.router.Route;
import io.zephyr.aire.layout.AireApplicationViewport;
import org.springframework.stereotype.Component;

@Component
@Route(value = "plugins/topology", layout = PluginManagementPage.class)
public class PluginTopologyPage extends Article {
  public PluginTopologyPage() {
    add(new Button("Topology"));
  }
}
