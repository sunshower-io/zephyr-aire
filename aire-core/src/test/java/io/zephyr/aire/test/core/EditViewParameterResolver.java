package io.zephyr.aire.test.core;

import io.zephyr.aire.api.ComponentDefinition;
import io.zephyr.aire.api.Registration;
import io.zephyr.aire.api.ViewContext;
import io.zephyr.aire.test.EditView;
import lombok.val;
import org.junit.jupiter.api.extension.*;

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
    val editView = method.getAnnotation(EditView.class);

    if (editView == null) {
      return;
    }

    if ("__none__".equals(editView.withField())) {
      return;
    }

    val viewContext = getViewContext(extensionContext);
    val type = extensionContext.getRequiredTestClass();
    val field = type.getDeclaredField(editView.withField());

    if (!ComponentDefinition.class.isAssignableFrom(field.getType())) {
      throw new IllegalArgumentException(
          String.format(
              "Error:  field named '%s' on type '%s' is not an instance of PropertyDefinition",
              field, type));
    }

    field.trySetAccessible();
    registrations.add(
        viewContext.register(
            (ComponentDefinition) field.get(extensionContext.getRequiredTestInstance())));
  }

  private ViewContext getViewContext(ExtensionContext extensionContext) {
    val ns = ExtensionContext.Namespace.create(extensionContext.getRequiredTestClass());
    val store = extensionContext.getStore(ns);
    return store.get(ViewContextParameterResolver.VIEW_CONTEXT, ViewContext.class);
  }
}
