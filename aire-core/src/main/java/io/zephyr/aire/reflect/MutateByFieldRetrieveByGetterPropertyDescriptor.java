package io.zephyr.aire.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

final class MutateByFieldRetrieveByGetterPropertyDescriptor extends AbstractPropertyDescriptor {
  private final Field field;
  private final Method getter;

  private boolean modifiedAccessibility;

  public MutateByFieldRetrieveByGetterPropertyDescriptor(
      String name, String alias, Class<?> type, Field field, Method getter) {
    super(name, alias, type);
    this.field = field;
    this.getter = getter;
    this.modifiedAccessibility = false;
  }

  @Override
  @SuppressWarnings("unchecked")
  public <T> T get(Object instance) throws ReflectiveOperationException {
    return (T) getter.invoke(instance);
  }

  @Override
  public <T> T set(Object instance, Object value) throws ReflectiveOperationException {
    T result = get(instance);
    if (field.canAccess(instance)) {
      field.set(instance, value);
    }

    if (!modifiedAccessibility) {
      field.setAccessible(true);
      modifiedAccessibility = true;
    }
    field.set(instance, value);
    return result;
  }
}
