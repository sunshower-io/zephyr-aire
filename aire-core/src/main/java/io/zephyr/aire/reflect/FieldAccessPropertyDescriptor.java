package io.zephyr.aire.reflect;

import lombok.val;

import java.lang.reflect.Field;

final class FieldAccessPropertyDescriptor extends AbstractPropertyDescriptor {
  private final Field field;

  private boolean modifiedAccessibility;

  public FieldAccessPropertyDescriptor(String name, String alias, Class<?> type, Field field) {
    super(name, alias, type);
    this.field = field;
    this.modifiedAccessibility = false;
  }

  @Override
  @SuppressWarnings("unchecked")
  public <T> T get(Object instance) throws ReflectiveOperationException {
    if (field.canAccess(instance)) {
      return (T) field.get(instance);
    }
    if (!modifiedAccessibility) {
      field.setAccessible(true);
      modifiedAccessibility = true;
    }

    return (T) field.get(instance);
  }

  @Override
  @SuppressWarnings("unchecked")
  public <T> T set(Object instance, Object value) throws ReflectiveOperationException {
    val result = get(instance);
    if (field.canAccess(instance)) {
      field.set(instance, value);
    }
    if (!modifiedAccessibility) {
      field.setAccessible(true);
      modifiedAccessibility = true;
      field.set(instance, value);
    }
    return (T) result;
  }
}
