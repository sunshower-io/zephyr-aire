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
    register(editView, extensionContext, type);

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

  private void register(EditView editView, ExtensionContext extensionContext, Class<?> type)
      throws ReflectiveOperationException {

    registerField(editView, extensionContext, type);
    registerMethod(editView, extensionContext, type);
  }

  private void registerMethod(EditView editView, ExtensionContext extensionContext, Class<?> type)
      throws ReflectiveOperationException {
    if (editView == null) {
      return;
    }

    val methods = new HashSet<String>();

    include(methods, editView.withMethod());

    for (val view : editView.withMethods()) {
      include(methods, view);
    }

    for (val methodName : methods) {
      decorateMethod(methodName, type, extensionContext);
    }
  }

  private void include(Set<String> methods, String view) {
    if (!Constants.NONE.equals(view)) {
      methods.add(view);
    }
  }

  private void registerField(EditView editView, ExtensionContext extensionContext, Class<?> type)
      throws ReflectiveOperationException {
    if (editView == null) {
      return;
    }

    val fieldNames = new HashSet<String>();

    include(fieldNames, editView.withField());

    for (val fieldName : editView.withFields()) {
      include(fieldNames, fieldName);
    }

    for (val fieldName : fieldNames) {
      decorateField(fieldName, extensionContext, type);
    }
  }

  private void decorateField(String fieldName, ExtensionContext extensionContext, Class<?> type)
      throws ReflectiveOperationException {
    val viewContext = getViewContext(extensionContext);
    val field = type.getDeclaredField(fieldName);

    if (!ComponentDefinition.class.isAssignableFrom(field.getType())) {
      throw new IllegalArgumentException(
          String.format(
              "Error:  field named '%s' on type '%s' is not an instance of PropertyDefinition",
              field, type));
    }

    val instance = extensionContext.getRequiredTestInstance();
    field.trySetAccessible();
    registrations.add(viewContext.register((ComponentDefinition<?>) field.get(instance)));
  }

  private void decorateMethod(String methodName, Class<?> type, ExtensionContext extensionContext)
      throws ReflectiveOperationException {

    val viewContext = getViewContext(extensionContext);
    val method = type.getDeclaredMethod(methodName);

    if (!ComponentDefinition.class.isAssignableFrom(method.getReturnType())) {
      throw new IllegalArgumentException(
          String.format(
              "Error:   method named '%s' on type '%s' is not an instance of PropertyDefinition",
              method, type));
    }
    val instance = extensionContext.getRequiredTestInstance();
    method.trySetAccessible();
    registrations.add(viewContext.register((ComponentDefinition<?>) method.invoke(instance)));
  }
}
