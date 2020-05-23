package io.zephyr.aire.api;

import io.zephyr.kernel.Module;

public interface ViewManager extends Registration {
  ViewContext newContext(Module module, Instantiator instantiator);

  Object decorate(Class<?> type, Object instance);
}
