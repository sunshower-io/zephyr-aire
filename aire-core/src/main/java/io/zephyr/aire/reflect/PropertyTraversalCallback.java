package io.zephyr.aire.reflect;

import java.lang.annotation.Annotation;

public interface PropertyTraversalCallback<T> {

  default void onRootOpen(Class<?> type) {}

  default void onRootClose(Class<?> type) {}

  default void onInvalidProperty(
      Class<?> owner, String propertyName, Class<? extends Annotation> annotationType) {}

  default void onProperty(PropertyDescriptor descriptor) {}

  default void onTraversalStart() {}

  default void onTraversalComplete() {}

  default T getResult() {
    return null;
  }
}
