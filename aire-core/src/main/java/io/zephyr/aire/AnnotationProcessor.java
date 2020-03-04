package io.zephyr.aire;

import io.zephyr.aire.api.ViewManager;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.MethodMetadata;

import java.lang.annotation.Annotation;

public abstract class AnnotationProcessor {

  final Class<? extends Annotation> annotation;

  protected AnnotationProcessor(Class<? extends Annotation> annotation) {
    this.annotation = annotation;
  }

  public abstract void process(
      MethodMetadata metadata,
      BeanDefinition definition,
      ViewManager viewManager,
      ConfigurableListableBeanFactory beanFactory);

  public abstract void process(
      AnnotatedTypeMetadata metadata,
      BeanDefinition definition,
      ViewManager viewManager,
      ConfigurableListableBeanFactory beanFactory);
}
