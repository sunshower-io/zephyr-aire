package io.zephyr.aire.ext;

import io.zephyr.aire.api.ComponentRegistry;
import io.zephyr.aire.api.ExtensionDefinition;

public class DefaultExtensionDefinition<T> implements ExtensionDefinition<T> {

  final int order;
  final String id;
  final String[] targets;
  final Class<T> component;

  public DefaultExtensionDefinition(int order, String id, String[] target, Class<T> component) {
    this.order = order;
    this.id = id;
    this.targets = target;
    this.component = component;
  }

  @Override
  public Class<T> getType() {
    return component;
  }

  @Override
  public String id() {
    return id;
  }

  @Override
  public int order() {
    return order;
  }

  @Override
  public String[] targets() {
    return targets;
  }

  @Override
  public T create(ComponentRegistry registry) {
    return registry.create(component);
  }
}
