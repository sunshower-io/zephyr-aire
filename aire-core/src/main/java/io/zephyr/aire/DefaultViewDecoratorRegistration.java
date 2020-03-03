package io.zephyr.aire;

import io.zephyr.aire.api.ViewDecorator;
import io.zephyr.aire.api.ViewDecoratorRegistration;

public class DefaultViewDecoratorRegistration implements ViewDecoratorRegistration {
  private final String key;
  private final Class<?> viewType;
  private final ViewDecorator<?> decorator;
  private final VaadinViewManager viewManager;

  public DefaultViewDecoratorRegistration(
      VaadinViewManager vaadinViewManager, Class<?> viewType, ViewDecorator<?> decorator) {
    this.key = null;
    this.viewType = viewType;
    this.decorator = decorator;
    this.viewManager = vaadinViewManager;
  }

  public <T> DefaultViewDecoratorRegistration(
      VaadinViewManager vaadinViewManager, String key, ViewDecorator<T> decorator) {
    this.key = key;
    this.viewType = null;
    this.decorator = decorator;
    this.viewManager = vaadinViewManager;
  }

  @Override
  @SuppressWarnings("unchecked")
  public void close() {
    if (viewType != null) {
      viewManager.removeRegistration((Class) viewType, decorator);
    } else if(key != null) {
      viewManager.removeRegistration(key, decorator);
    }
  }
}
