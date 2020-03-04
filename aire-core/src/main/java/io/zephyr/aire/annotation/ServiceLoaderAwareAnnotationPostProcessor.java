package io.zephyr.aire.annotation;

import io.aire.ext.AnnotationProcessor;
import io.zephyr.aire.api.ViewManager;
import io.zephyr.kernel.Module;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class ServiceLoaderAwareAnnotationPostProcessor
    implements BeanPostProcessor, ApplicationContextAware {

  static final Logger log =
      LoggerFactory.getLogger(ServiceLoaderAwareAnnotationPostProcessor.class);
  private ApplicationContext context;

  List<AnnotationProcessor> processors;

  @Override
  public Object postProcessAfterInitialization(Object bean, String name) throws BeansException {
    if (processors == null) {
      loadProcessors();
    }

    val type = AopUtils.getTargetClass(bean);
    val procitr = processors.iterator();
    while (procitr.hasNext()) {
      val processor = procitr.next();
      if (processor.handles(bean, type)) {
        val viewManager = context.getBean(ViewManager.class);
        val module = context.getBean(Module.class);
        processor.process(viewManager, module, bean, type);
      }
    }
    return bean;
  }

  private List<AnnotationProcessor> loadProcessors() {
    val classloader = context.getClassLoader();
    log.info("Loading annotation processors from classloader {}", classloader);
    val serviceLoader = ServiceLoader.load(AnnotationProcessor.class, classloader).iterator();
    val results = new ArrayList<AnnotationProcessor>();
    while (serviceLoader.hasNext()) {
      val next = serviceLoader.next();
      log.info("Resolved annotation processor {} processing {}", next, next.getAnnotationType());
      results.add(next);
    }
    return (processors = results);
  }

  @Override
  public void setApplicationContext(ApplicationContext ctx) throws BeansException {
    this.context = ctx;
  }
}
