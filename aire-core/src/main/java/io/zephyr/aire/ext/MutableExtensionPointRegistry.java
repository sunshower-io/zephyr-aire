package io.zephyr.aire.ext;

import io.zephyr.aire.api.ExtensionPointDefinition;
import io.zephyr.aire.api.ExtensionPointRegistry;

import java.util.Collection;

public interface MutableExtensionPointRegistry extends ExtensionPointRegistry {

  <T> void register(ExtensionPointDefinition<T> definition);
//
//  <T> ExtensionPointDefinition<T> getParent(String name);
//
//  Collection<ExtensionPointDefinition<?>> getChildren(String name);
}
