package io.zephyr.admin;

import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinService;
import io.zephyr.admin.ui.PluginExtension;
import io.zephyr.admin.ui.PluginListPage;
import io.zephyr.admin.ui.PluginTopologyPage;
import io.zephyr.aire.api.ViewContext;
import io.zephyr.aire.elements.FontAwesome;
import io.zephyr.aire.servlet.AireVaadinServlet;
import lombok.val;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import static io.zephyr.aire.api.Views.*;

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

    viewContext.register(
        appendWith(
                () -> {
                  val routerLink = new RouterLink();
                  VaadinService.setCurrent(AireVaadinServlet.getInstance().getService());
                  routerLink.setRoute(PluginListPage.class);
                  routerLink.add(FontAwesome.Plug.icon());
                  return routerLink;
                })
            .to(":main:primary-navigation"));
  }
}
