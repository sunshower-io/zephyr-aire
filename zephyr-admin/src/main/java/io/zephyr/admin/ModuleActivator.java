package io.zephyr.admin;

import io.zephyr.admin.ui.PluginListPage;
import io.zephyr.admin.ui.PluginTopologyPage;
import io.zephyr.aire.api.ViewManager;
import io.zephyr.api.ModuleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ModuleActivator implements io.zephyr.api.ModuleActivator {

  static final Logger log = LoggerFactory.getLogger(ModuleActivator.class);
  private AnnotationConfigApplicationContext context;

  @Override
  public void start(ModuleContext moduleContext) {
    log.info("zephyr-admin starting...");
    ApplicationContext parentContext = moduleContext.get(ApplicationContext.class);

    context = new AnnotationConfigApplicationContext();
    context.setParent(parentContext);
    context.register(ZephyrAdminConfiguration.class);

    context.refresh();
    log.info("zephyr-admin started successfully");
  }

  @Override
  public void stop(ModuleContext moduleContext) throws Exception {
    log.info("zephyr-admin stopping...");
    context.close();
    log.info("zephyr-admin stopped successfully");
  }
}
