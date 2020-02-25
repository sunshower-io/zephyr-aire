package io.zephyr.aire;

import io.zephyr.api.ModuleActivator;
import io.zephyr.api.ModuleContext;

public class AireModuleActivator implements ModuleActivator {
  @Override
  public void start(ModuleContext moduleContext) throws Exception {
    System.out.println("starting...");
  }

  @Override
  public void stop(ModuleContext moduleContext) throws Exception {
    System.out.println("stopping...");
  }
}
