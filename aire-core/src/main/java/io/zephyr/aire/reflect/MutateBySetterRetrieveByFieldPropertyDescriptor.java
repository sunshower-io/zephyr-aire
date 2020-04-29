package io.zephyr.aire.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MutateBySetterRetrieveByFieldPropertyDescriptor extends AbstractPropertyDescriptor {
  private final Field field;
  private final Method setter;

  private boolean modifiedAccessibility;

  public MutateBySetterRetrieveByFieldPropertyDescriptor(
      String name, String alias, Class<?> type, Field field, Method setter) {
    super(name, alias, type);
    this.field = field;
    this.setter = setter;
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
  public <T> T set(Object instance, Object value) throws ReflectiveOperationException {

    T result = get(instance);
    setter.invoke(instance, value);
    return result;
  }
}
