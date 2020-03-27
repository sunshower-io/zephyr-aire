package io.zephyr.aire.api;

public interface ViewManager {

  boolean hasRoute(Class<?> route);

  boolean registerRoute(Class<?> route);

  boolean unregisterRoute(Class<?> route);

  ComponentRegistry getComponentRegistry();

  ExtensionPointRegistry getExtensionPointRegistry();
}
