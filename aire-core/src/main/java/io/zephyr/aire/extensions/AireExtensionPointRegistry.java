package io.zephyr.aire.extensions;

import io.zephyr.aire.api.ExtensionPointDefinition;
import io.zephyr.aire.api.ExtensionPointRegistry;
import lombok.val;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AireExtensionPointRegistry implements ExtensionPointRegistry {

  private final List<ExtensionPointDefinition> definitions;

  public AireExtensionPointRegistry() {
    definitions = new ArrayList<>();
  }

  @Override
  public void registerExtensionPoint(ExtensionPointDefinition definition) {
    definitions.add(definition);
  }

  @Override
  public void unregisterExtensionPoint(ExtensionPointDefinition definition) {}

  @Override
  public List<ExtensionPointDefinition> getExtensionPoints() {
    return definitions;
  }

  @Override
  public boolean containsDefinition(String beanName) {
    for(val def : definitions) {
      if(def.getId().equals(beanName)) {
        return true;
      }
    }
    return false;
  }
}
