package io.zephyr.aire;

import io.zephyr.aire.api.ExtensionPointRegistry;
import io.zephyr.aire.api.ViewManager;

public class VaadinViewManager implements ViewManager {

  private final ExtensionPointRegistry registry;

  public VaadinViewManager(final ExtensionPointRegistry registry) {
    this.registry = registry;
  }

  @Override
  public ExtensionPointRegistry getExtensionPointRegistry() {
    return registry;
  }
}
