package io.zephyr.aire.reflect;

import java.lang.reflect.Method;

final class MutatorExclusivePropertyDescriptor extends AbstractPropertyDescriptor {
  private final Method getter;
  private final Method setter;

  public MutatorExclusivePropertyDescriptor(
      String name, String alias, Class<?> type, Method getter, Method setter) {
    super(name, alias, type);
    this.getter = getter;
    this.setter = setter;
  }

  @Override
  @SuppressWarnings("unchecked")
  public <T> T get(Object instance) throws ReflectiveOperationException {
    return (T) getter.invoke(instance);
  }

  @Override
  public <T> T set(Object instance, Object value) throws ReflectiveOperationException {
    final T result = get(instance);
    setter.invoke(instance, value);
    return result;
  }
}
