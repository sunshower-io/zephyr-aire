package io.zephyr.aire.reflect;

public interface AnnotatedPropertySet {

  int size();

  PropertyDescriptor get(String name);

  PropertyDescriptor getAliased(String alias);
}
