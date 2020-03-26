package io.zephyr.aire.api;

import java.util.List;

public interface ExtensionPointRegistry {

  List<ExtensionPointDefinition<?>> getExtensionPoints();

  boolean containsDefinition(String name);

  <T> ExtensionPointDefinition<T> getDefinition(String name);

  List<ExtensionPointDefinition<?>> getChildren(String s);
}
