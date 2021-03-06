package io.zephyr.admin;

import io.zephyr.aire.api.ViewContext;
import io.zephyr.aire.api.ViewManager;
import io.zephyr.aire.core.ui.VaadinBridge;
import io.zephyr.api.ModuleContext;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ModuleActivator implements io.zephyr.api.ModuleActivator {

  static final Logger log = LoggerFactory.getLogger(ModuleActivator.class);
  private AnnotationConfigApplicationContext context;
  private ViewContext viewContext;

  @Override
  public void start(ModuleContext moduleContext) {
    log.info("zephyr-admin starting...");
    ApplicationContext parentContext = moduleContext.get(ApplicationContext.class);
    val viewManager = parentContext.getBean(ViewManager.class);

    context = new AnnotationConfigApplicationContext();
    context.setParent(parentContext);
    context.register(ZephyrAdminConfiguration.class);
    viewContext = viewManager.newContext(moduleContext.getModule(), new VaadinBridge(context));
    context.getBeanFactory().registerSingleton("viewContext", viewContext);

    context.refresh();
    log.info("zephyr-admin started successfully");
  }

  @Override
  public void stop(ModuleContext moduleContext) throws Exception {
    log.info("zephyr-admin stopping...");
    context.close();
    viewContext.close();
    log.info("zephyr-admin stopped successfully");
  }
}
