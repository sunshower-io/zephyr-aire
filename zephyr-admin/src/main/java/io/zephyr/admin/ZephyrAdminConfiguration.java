package io.zephyr.admin;

import io.zephyr.admin.ui.PluginExtension;
import io.zephyr.admin.ui.PluginListPage;
import io.zephyr.admin.ui.PluginManagementPage;
import io.zephyr.admin.ui.PluginTopologyPage;
import io.zephyr.aire.api.ViewManager;
import lombok.val;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

@Configuration
public class ZephyrAdminConfiguration implements ApplicationListener<ContextRefreshedEvent> {

  @Bean
  public PluginExtension pluginExtension() {
    return new PluginExtension();
  }

  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    val ctx = contextRefreshedEvent.getApplicationContext();
    val viewManager = ctx.getBean(ViewManager.class);
    viewManager.registerRoute(PluginListPage.class);
    viewManager.registerRoute(PluginTopologyPage.class);
    viewManager.getComponentRegistry().register(PluginExtension.class);
  }
}
