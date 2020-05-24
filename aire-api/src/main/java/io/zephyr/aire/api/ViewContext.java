package io.zephyr.aire.api;

import io.zephyr.kernel.Module;

import java.util.List;
import java.util.Set;

public interface ViewContext extends Registration {

  Module getHost();

  void close();

  Set<ComponentDefinition<?>> getComponentDefinitions();

  <T> Registration register(ComponentDefinition<T> componentDefinition);
}
