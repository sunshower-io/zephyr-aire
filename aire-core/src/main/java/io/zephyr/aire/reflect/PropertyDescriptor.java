package io.zephyr.aire.reflect;

public interface PropertyDescriptor {

  Class<?> getType();

  String getName();

  String getAlias();

  <T> T get(Object instance) throws ReflectiveOperationException;

  <T> T set(Object instance, Object value) throws ReflectiveOperationException;
}
