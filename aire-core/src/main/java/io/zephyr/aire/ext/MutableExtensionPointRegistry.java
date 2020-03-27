package io.zephyr.aire.ext;

import io.zephyr.aire.api.ComponentRegistry;
import io.zephyr.aire.api.ExtensionDefinition;
import io.zephyr.aire.api.ExtensionPointDefinition;
import io.zephyr.aire.api.ExtensionPointRegistry;


public interface MutableExtensionPointRegistry extends ExtensionPointRegistry, ComponentRegistry {

  <T> void register(ExtensionPointDefinition<T> definition);

  <T> void decorate(Class<T> type, T instance, ExtensionPointDefinition<T> definition);


  <T> ExtensionDefinition<?>[] extensionsFor(ExtensionPointDefinition<T> definition);
}
