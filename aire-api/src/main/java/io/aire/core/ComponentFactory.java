package io.aire.core;

public interface ComponentFactory<E extends Enum<E>> {

  AireComponent create(E region);

  @SuppressWarnings("unchecked")
  default <T extends AireComponent> T createIfNull(E region, T current) {
    if (current == null) {
      return (T) create(region);
    }
    return current;
  }
}
