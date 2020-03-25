package io.aire.ext;

import io.zephyr.aire.api.ViewManager;
import io.zephyr.kernel.Module;

import java.lang.annotation.Annotation;

public interface AnnotationProcessor {
  void process(ViewManager viewManager, Module module, Object annotationDefinition, Class<?> type);

  Class<? extends Annotation> getAnnotationType();

  boolean handles(Object instance, Class<?> type);
}
