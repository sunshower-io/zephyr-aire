package io.zephyr.aire.ext;

import io.zephyr.aire.api.ExtensionPointDefinition;

public class DefaultExtensionPointDefinition<T> implements ExtensionPointDefinition<T> {
  private final Class<T> type;
  private final String location;

  public DefaultExtensionPointDefinition(Class<T> type, String location) {
    this.type = type;
    this.location = location;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof DefaultExtensionPointDefinition)) return false;

    DefaultExtensionPointDefinition<?> that = (DefaultExtensionPointDefinition<?>) o;

    if (!type.equals(that.type)) return false;
    return location.equals(that.location);
  }

  @Override
  public int hashCode() {
    int result = type.hashCode();
    result = 31 * result + location.hashCode();
    return result;
  }

  @Override
  public Class<T> getType() {
    return type;
  }

  @Override
  public String getLocation() {
    return location;
  }

}
