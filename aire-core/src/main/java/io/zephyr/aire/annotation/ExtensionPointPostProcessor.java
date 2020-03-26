package io.zephyr.aire.annotation;

import io.zephyr.aire.api.ExtensionPoint;
import io.zephyr.aire.api.ExtensionPointRegistry;
import io.zephyr.aire.ext.MutableExtensionPointRegistry;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import javax.inject.Inject;
import java.beans.IntrospectionException;
import java.beans.Introspector;

@Slf4j
public class ExtensionPointPostProcessor implements BeanPostProcessor {

  private final ExtensionPointRegistry registry;

  @Inject
  public ExtensionPointPostProcessor(final ExtensionPointRegistry registry) {
    this.registry = registry;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

    val scanner = new ExtensionPointScanner((MutableExtensionPointRegistry) registry);

    try {
      val type = AopUtils.getTargetClass(bean);
      if (type.isAnnotationPresent(ExtensionPoint.class)) {
        scanner.scan(type, type.getAnnotation(ExtensionPoint.class));
      }
    } catch (IntrospectionException ex) {
      log.warn("Failed to register");
    }
    return bean;
  }
}
