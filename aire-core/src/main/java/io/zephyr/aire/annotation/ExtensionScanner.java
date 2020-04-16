package io.zephyr.aire.annotation;

import com.vaadin.flow.component.Component;
import io.zephyr.aire.api.Extension;
import io.zephyr.aire.api.ExtensionDefinition;
import io.zephyr.aire.ext.DefaultExtensionDefinition;
import io.zephyr.aire.ext.MutableExtensionPointRegistry;
import lombok.val;

public class ExtensionScanner {

  private final MutableExtensionPointRegistry registry;

  public ExtensionScanner(MutableExtensionPointRegistry registry) {
    this.registry = registry;
  }

  public <T> ExtensionDefinition<T> scan(Class<T> type) {
    if (!type.isAnnotationPresent(Extension.class)) {
      return null;
    }

    val extension = type.getAnnotation(Extension.class);

    val id = resolveActualId(extension.id(), type);

    if (registry.containsExtension(id)) {
      return registry.getExtension(id);
    }

    String[] targets = extension.targets();

    if (targets.length == 0 || "__VALUE__".equals(targets[0])) {
      targets = extension.value();
    }
    if (targets.length == 0 || "__VALUE__".equals(targets[0])) {
      throw new IllegalArgumentException("Error:  an extension must have at least 1 target");
    }

    val definition = new DefaultExtensionDefinition<T>(extension.order(), id, targets, type);
    registry.register(definition);
    return definition;
  }

  private String resolveActualId(String id, Class<?> type) {
    if ("__GENERATED__".equals(id)) {
      return type.getName();
    }

    return id;
  }
}
