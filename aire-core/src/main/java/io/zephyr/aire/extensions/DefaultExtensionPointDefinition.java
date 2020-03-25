package io.zephyr.aire.extensions;

import io.zephyr.aire.api.ExtensionPointDefinition;

public class DefaultExtensionPointDefinition implements ExtensionPointDefinition {

  private final String extensionPointName;

  public DefaultExtensionPointDefinition(String extensionPointName) {
    this.extensionPointName = extensionPointName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof DefaultExtensionPointDefinition)) return false;

    DefaultExtensionPointDefinition that = (DefaultExtensionPointDefinition) o;

    return extensionPointName != null
        ? extensionPointName.equals(that.extensionPointName)
        : that.extensionPointName == null;
  }

  @Override
  public int hashCode() {
    return extensionPointName != null ? extensionPointName.hashCode() : 0;
  }

  @Override
  public String getId() {
    return extensionPointName;
  }
}
