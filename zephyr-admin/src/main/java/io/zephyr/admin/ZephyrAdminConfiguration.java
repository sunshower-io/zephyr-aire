package io.zephyr.admin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinService;
import io.zephyr.admin.ui.PluginExtension;
import io.zephyr.admin.ui.PluginListPage;
import io.zephyr.admin.ui.PluginManagementPage;
import io.zephyr.admin.ui.PluginTopologyPage;
import io.zephyr.aire.api.Registration;
import io.zephyr.aire.api.ViewContext;
import io.zephyr.aire.api.ViewManager;
import io.zephyr.aire.elements.FontAwesome;
import io.zephyr.aire.servlet.AireVaadinServlet;
import lombok.val;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import static io.zephyr.aire.api.Views.append;
import static io.zephyr.aire.api.Views.appendInstance;

@Configuration
public class ZephyrAdminConfiguration implements ApplicationListener<ContextRefreshedEvent> {

  @Bean
  public PluginExtension pluginExtension() {
    return new PluginExtension();
  }

  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    val ctx = contextRefreshedEvent.getApplicationContext();
    val viewContext = ctx.getBean(ViewContext.class);

    viewContext.registerRoute(ViewContext.Scope.Session, PluginListPage.class);
    viewContext.registerRoute(ViewContext.Scope.Session, PluginTopologyPage.class);

    val routerLink = new RouterLink();
    VaadinService.setCurrent(AireVaadinServlet.getInstance().getService());
    routerLink.setRoute(PluginListPage.class);
    routerLink.add(FontAwesome.Plug.icon());
    viewContext.register(appendInstance(routerLink).to(":main:primary-navigation"));
  }
}
