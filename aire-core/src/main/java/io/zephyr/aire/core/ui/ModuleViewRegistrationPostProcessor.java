package io.zephyr.aire.core.ui;

import io.zephyr.aire.api.ViewManager;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class ModuleViewRegistrationPostProcessor implements BeanPostProcessor {

  private final ViewManager viewManager;

  public ModuleViewRegistrationPostProcessor(ViewManager viewManager) {
    this.viewManager = viewManager;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    return viewManager.decorate(AopUtils.getTargetClass(bean), bean);
  }
}
