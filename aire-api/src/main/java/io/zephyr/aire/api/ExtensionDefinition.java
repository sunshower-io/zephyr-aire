package io.zephyr.aire.api;


public interface ExtensionDefinition<T> {

  Class<T> getType();

  /** @return the name of this extension */
  String id();

  int order();

  String[] targets();

  T create(ComponentRegistry registry);
}
