package io.zephyr.admin.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Article;
import com.vaadin.flow.router.Route;
import org.springframework.stereotype.Component;

@Component
@Route(value = "topology", layout = PluginManagementPage.class)
public class PluginTopologyPage extends Article {
  public PluginTopologyPage() {
    add(new Button("Topology"));
  }
}
