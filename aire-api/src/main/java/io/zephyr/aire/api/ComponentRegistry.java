package io.zephyr.aire.api;


public interface ComponentRegistry {

  <T> T create(Class<T> type);

  <T> void register(Class<T> extension);

  <T> void register(ExtensionDefinition<T> definition);

  boolean containsExtension(String id);

  <T> ExtensionDefinition<T> getExtension(String id);
}
