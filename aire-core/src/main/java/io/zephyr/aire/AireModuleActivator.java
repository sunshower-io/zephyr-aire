package io.zephyr.aire;

import io.zephyr.api.ModuleActivator;
import io.zephyr.api.ModuleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AireModuleActivator implements ModuleActivator {

  static final Logger log = LoggerFactory.getLogger(AireModuleActivator.class);

  @Override
  public void start(ModuleContext moduleContext) throws Exception {
    log.info("zephyr-aire is starting...");
    log.info("zephyr-aire has started successfully...");
  }

  @Override
  public void stop(ModuleContext moduleContext) throws Exception {
    log.info("zephyr-aire is shutting down");
    log.info("zephyr-aire has successfully shut down");
  }
}
