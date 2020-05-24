package io.zephyr.aire.core.ui;

import io.zephyr.aire.api.ComponentDefinition;
import io.zephyr.aire.api.Instantiator;
import io.zephyr.aire.api.Registration;
import io.zephyr.aire.api.ViewContext;
import io.zephyr.kernel.Module;
import lombok.val;

import java.util.Set;

public class VaadinViewContext implements ViewContext {

  private final Module host;
  private final Instantiator instantiator;
  private final VaadinViewManager viewManager;

  public VaadinViewContext(VaadinViewManager viewManager, Module host, Instantiator instantiator) {
    this.host = host;
    this.instantiator = instantiator;
    this.viewManager = viewManager;
  }

  @Override
  public Module getHost() {
    return host;
  }

  @Override
  public void close() {
    viewManager.unregister(this);
  }

  @Override
  public Set<ComponentDefinition<?>> getComponentDefinitions() {
    return viewManager.getDefinitions(host.getCoordinate());
  }

  @Override
  public <T> Registration register(ComponentDefinition<T> componentDefinition) {
    for (val location : componentDefinition.extensionPointLocations()) {
      viewManager.registerDefinition(
          location, componentDefinition, instantiator, host.getCoordinate());
    }
    return null;
  }
}
