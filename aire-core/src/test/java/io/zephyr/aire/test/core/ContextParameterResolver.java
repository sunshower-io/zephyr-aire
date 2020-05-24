package io.zephyr.aire.test.core;

import lombok.val;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import static io.zephyr.aire.test.core.ApplicationTrackerListener.getContext;

public class ContextParameterResolver implements ParameterResolver {

  @Override
  public boolean supportsParameter(
      ParameterContext parameterContext, ExtensionContext extensionContext)
      throws ParameterResolutionException {
    val beanNames = getContext().getBeanNamesForType(getParameterType(parameterContext));
    return beanNames.length > 0;
  }

  @Override
  public Object resolveParameter(
      ParameterContext parameterContext, ExtensionContext extensionContext)
      throws ParameterResolutionException {
    return getContext().getBean(getParameterType(parameterContext));
  }

  private Class<?> getParameterType(ParameterContext parameterContext) {
    val parameter = parameterContext.getParameter();
    return parameter.getType();
  }
}
