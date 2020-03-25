package io.zephyr.aire.annotation;

import io.zephyr.aire.api.ExtensionPoint;
import io.zephyr.aire.api.ExtensionPointRegistry;
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

  @Inject private ExtensionPointRegistry registry;

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

    val type = AopUtils.getTargetClass(bean);
    if (type.isAnnotationPresent(ExtensionPoint.class)) {

      val extensionPoint = type.getAnnotation(ExtensionPoint.class);


      if (!registry.containsDefinition(beanName)) {

        try {
          scanDefinition(beanName, type);
        } catch (IntrospectionException ex) {
        }
      }
    }
    return bean;
  }

  private void scanDefinition(String beanName, Class<?> type) throws IntrospectionException {
    val descriptor = Introspector.getBeanInfo(type);

    for(val propertyDescriptor : descriptor.getPropertyDescriptors()) {

    }
  }
}
