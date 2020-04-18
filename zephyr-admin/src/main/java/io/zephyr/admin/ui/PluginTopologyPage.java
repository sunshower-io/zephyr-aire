package io.zephyr.admin.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Article;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.Route;
import io.zephyr.aire.api.Decorate;
import io.zephyr.aire.components.AireAsideDrawerMenu;
import io.zephyr.aire.layout.AireApplicationViewport;
import lombok.val;
import org.springframework.stereotype.Component;

@Decorate
@Component
@Route(value = "topology", layout = PluginManagementPage.class)
public class PluginTopologyPage extends Article {
  public PluginTopologyPage() {
    add(new Button("Topology"));
  }

  @Decorate
  public void decorate(AireApplicationViewport viewport) {
    val instance = new AireAsideDrawerMenu();
    val btn = new Button();
    btn.setIcon(new Icon(VaadinIcon.QUESTION_CIRCLE));
    instance.add(btn, PluginTopologyPage.class);
    viewport.setSecondaryNavigation(instance);
  }
}
