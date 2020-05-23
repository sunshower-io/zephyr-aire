package io.zephyr.aire.api;

import io.zephyr.kernel.Module;

public interface ViewContext extends Registration {

  Module getHost();

  void close();

  <T> Registration register(ComponentDefinition<T> componentDefinition);
}
