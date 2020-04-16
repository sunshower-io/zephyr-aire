package io.zephyr.admin.ui;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import io.zephyr.aire.layout.AireApplicationViewport;
import io.zephyr.kernel.core.Kernel;
import lombok.val;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
@Route(value = "plugins/list", layout = PluginManagementPage.class)
public class PluginListPage extends VerticalLayout {

  @Inject
  public PluginListPage(final Kernel kernel) {
    this.setPadding(true);
    val plugins = kernel.getModuleManager().getModules();
    for (val plugin : plugins) {
      add(new ModuleCard(plugin));
    }
  }
}
