package io.zephyr.aire.test.core;

import io.zephyr.aire.api.ComponentDefinition;
import io.zephyr.aire.api.Registration;
import io.zephyr.aire.api.ViewContext;
import io.zephyr.aire.test.EditView;
import io.zephyr.aire.test.ViewTest;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.*;
import org.junit.platform.commons.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class EditViewParameterResolver implements BeforeEachCallback, AfterEachCallback {

  private final Set<Registration> registrations = new HashSet<>();

  @Override
  public void afterEach(ExtensionContext extensionContext) throws Exception {
    getViewContext(extensionContext).close();
  }

  @Override
  @SuppressWarnings({"unchecked", "rawtypes"})
  public void beforeEach(ExtensionContext extensionContext) throws Exception {
    val method = extensionContext.getRequiredTestMethod();
    val type = extensionContext.getRequiredTestClass();
    val editView = method.getAnnotation(EditView.class);
    register(method, editView, extensionContext, type);

    val methods =
        ReflectionUtils.findMethods(
            type, this::findAnnotatedMethods, ReflectionUtils.HierarchyTraversalMode.BOTTOM_UP);

    val viewContext = getViewContext(extensionContext);
    val instance = extensionContext.getRequiredTestInstance();
    for (val m : methods) {
      m.trySetAccessible();
      registrations.add(viewContext.register((ComponentDefinition) m.invoke(instance)));
    }
  }

  private boolean findAnnotatedMethods(Method method) {
    return method.getParameterCount() == 0
        && method.isAnnotationPresent(EditView.class)
        && !(method.isAnnotationPresent(Test.class) || method.isAnnotationPresent(ViewTest.class));
  }

  private ViewContext getViewContext(ExtensionContext extensionContext) {
    val ns = ExtensionContext.Namespace.create(extensionContext.getRequiredTestClass());
    val store = extensionContext.getStore(ns);
    return store.get(ViewContextParameterResolver.VIEW_CONTEXT, ViewContext.class);
  }

  private void register(
      Method method, EditView editView, ExtensionContext extensionContext, Class<?> type)
      throws ReflectiveOperationException {

    registerField(editView, extensionContext, type);
    registerMethod(editView, extensionContext, type);
  }

  private void registerMethod(EditView editView, ExtensionContext extensionContext, Class<?> type)
      throws ReflectiveOperationException {
    if (editView == null) {
      return;
    }

    if ("__none__".equals(editView.withMethod())) {
      return;
    }

    val viewContext = getViewContext(extensionContext);
    val method = type.getDeclaredMethod(editView.withMethod());

    if (!ComponentDefinition.class.isAssignableFrom(method.getReturnType())) {
      throw new IllegalArgumentException(
          String.format(
              "Error:   method named '%s' on type '%s' is not an instance of PropertyDefinition",
              method, type));
    }

    val instance = extensionContext.getRequiredTestInstance();
    method.trySetAccessible();
    registrations.add(viewContext.register((ComponentDefinition) method.invoke(instance)));
  }

  private void registerField(EditView editView, ExtensionContext extensionContext, Class<?> type)
      throws NoSuchFieldException, IllegalAccessException {
    if (editView == null) {
      return;
    }

    if ("__none__".equals(editView.withField())) {
      return;
    }

    val viewContext = getViewContext(extensionContext);
    val field = type.getDeclaredField(editView.withField());

    if (!ComponentDefinition.class.isAssignableFrom(field.getType())) {
      throw new IllegalArgumentException(
          String.format(
              "Error:  field named '%s' on type '%s' is not an instance of PropertyDefinition",
              field, type));
    }

    val instance = extensionContext.getRequiredTestInstance();
    field.trySetAccessible();
    registrations.add(viewContext.register((ComponentDefinition) field.get(instance)));
  }
}
