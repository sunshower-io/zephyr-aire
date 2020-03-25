package io.zephyr.aire.annotation;

import io.zephyr.aire.api.ExtensionPoint;
import io.zephyr.aire.api.ExtensionPointRegistry;
import io.zephyr.aire.extensions.DefaultExtensionPointDefinition;
import lombok.val;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import javax.inject.Inject;

public class ExtensionPointPostProcessor implements BeanPostProcessor {

  @Inject private ExtensionPointRegistry registry;

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

    val type = AopUtils.getTargetClass(bean);
    if (type.isAnnotationPresent(ExtensionPoint.class)) {
      if (!registry.containsDefinition(beanName)) {
        registry.registerExtensionPoint(new DefaultExtensionPointDefinition(beanName));
      }
    }
    return bean;
  }
}
