package io.zephyr.aire.annotation;

import com.vaadin.flow.component.UI;
import io.zephyr.aire.api.ExtensionPoint;
import io.zephyr.aire.api.ExtensionPointDefinition;
import io.zephyr.aire.ext.MutableExtensionPointRegistry;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import javax.inject.Inject;
import java.beans.IntrospectionException;

@Slf4j
public class ExtensionPointPostProcessor implements BeanPostProcessor {

  private final MutableExtensionPointRegistry registry;
  private final ExtensionPointScanner extensionPointScanner;

  @Inject
  public ExtensionPointPostProcessor(
      final MutableExtensionPointRegistry registry,
      final ExtensionPointScanner extensionPointScanner) {
    this.registry = registry;
    this.extensionPointScanner = extensionPointScanner;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    val type = AopUtils.getTargetClass(bean);


    if (scanForExtensionPoints(type, bean)) {
      log.info("Successfully registered extension point with type: {}", type);
    }
    return bean;
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  final boolean scanForExtensionPoints(Class<?> type, Object bean) {
    try {
      if (type.isAnnotationPresent(ExtensionPoint.class)) {
        log.info("Inspecting extension point on {}", type);
        val definition = extensionPointScanner.scan(type, type.getAnnotation(ExtensionPoint.class));

        if (definition != null) {
          registry.decorate((Class) type, bean, (ExtensionPointDefinition) definition);
        }
      }
    } catch (IntrospectionException ex) {
      log.warn("Failed to register");
    }
    return false;
  }
}
