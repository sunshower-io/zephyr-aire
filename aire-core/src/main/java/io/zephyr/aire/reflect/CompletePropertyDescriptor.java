package io.zephyr.aire.reflect;

import lombok.val;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

final class CompletePropertyDescriptor extends AbstractPropertyDescriptor {

  private final Field field;
  private final Method getter;
  private final Method setter;

  public CompletePropertyDescriptor(
      String name, String alias, Class<?> type, Field field, Method getter, Method setter) {
    super(name, alias, type);
    this.field = field;
    this.getter = getter;
    this.setter = setter;
  }

  @Override
  @SuppressWarnings("unchecked")
  public <T> T get(Object instance) throws ReflectiveOperationException {
    return (T) getter.invoke(instance);
  }

  @Override
  @SuppressWarnings("unchecked")
  public <T> T set(Object instance, Object value) throws ReflectiveOperationException {
    val result = get(instance);
    setter.invoke(instance, value);
    return (T) result;
  }
}
