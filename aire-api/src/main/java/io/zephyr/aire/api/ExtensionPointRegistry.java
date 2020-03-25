package io.zephyr.aire.api;

import java.util.List;

public interface ExtensionPointRegistry {

  void registerExtensionPoint(ExtensionPointDefinition definition);

  void unregisterExtensionPoint(ExtensionPointDefinition definition);

  List<ExtensionPointDefinition> getExtensionPoints();

  boolean containsDefinition(String beanName);
}
