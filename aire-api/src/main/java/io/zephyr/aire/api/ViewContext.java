package io.zephyr.aire.api;

import io.zephyr.kernel.Module;

import java.util.Set;

public interface ViewContext extends Registration {

  enum Scope {
    Session,
    Application
  }

  Module getHost();

  void close();

  Set<ComponentDefinition<?>> getComponentDefinitions();

  <T> Registration register(ComponentDefinition<T> componentDefinition);

  <T> Registration registerRoute(Scope scope, Class<T> route);
}
