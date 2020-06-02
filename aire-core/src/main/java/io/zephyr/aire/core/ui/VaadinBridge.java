package io.zephyr.aire.core.ui;

import io.zephyr.aire.api.Instantiator;
import org.springframework.context.ApplicationContext;

public class VaadinBridge implements Instantiator {
  final ApplicationContext context;

  public VaadinBridge(ApplicationContext context) {
    this.context = context;
  }

  @Override
  public <T> T instantiate(Class<T> type) {
    return context.getAutowireCapableBeanFactory().createBean(type);
  }
}
