package io.zephyr.aire.api;

public interface ExtensionPointDefinition<T> {
  String getLocation();

  Class<T> getType();
}
