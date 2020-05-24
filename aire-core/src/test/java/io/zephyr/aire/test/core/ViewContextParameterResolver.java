package io.zephyr.aire.test.core;

import io.zephyr.aire.api.ViewContext;
import io.zephyr.aire.api.ViewManager;
import io.zephyr.aire.core.ui.VaadinBridge;
import io.zephyr.kernel.Module;
import lombok.val;
import org.junit.jupiter.api.extension.*;

import static io.zephyr.aire.test.core.ApplicationTrackerListener.getContext;

public class ViewContextParameterResolver
    implements ParameterResolver, AfterEachCallback, BeforeEachCallback {
  static final String VIEW_CONTEXT = "ViewContext";

  @Override
  public boolean supportsParameter(
      ParameterContext parameterContext, ExtensionContext extensionContext)
      throws ParameterResolutionException {
    return supported(parameterContext);
  }

  @Override
  public Object resolveParameter(
      ParameterContext parameterContext, ExtensionContext extensionContext)
      throws ParameterResolutionException {

    val ns = ExtensionContext.Namespace.create(extensionContext.getRequiredTestClass());
    val store = extensionContext.getStore(ns);
    return store.get(VIEW_CONTEXT, ViewContext.class);
  }

  @Override
  public void afterEach(ExtensionContext extensionContext) throws Exception {
    val ns = ExtensionContext.Namespace.create(extensionContext.getRequiredTestClass());
    val store = extensionContext.getStore(ns);
    val viewContext = store.get(VIEW_CONTEXT, ViewContext.class);
    viewContext.close();
    store.remove(VIEW_CONTEXT);
  }

  @Override
  public void beforeEach(ExtensionContext extensionContext) throws Exception {
    val ns = ExtensionContext.Namespace.create(extensionContext.getRequiredTestClass());
    val store = extensionContext.getStore(ns);
    store.getOrComputeIfAbsent(
        VIEW_CONTEXT,
        s -> {
          val context = getContext();
          val module = context.getBean(Module.class);
          val viewManager = context.getBean(ViewManager.class);
          return viewManager.newContext(module, new VaadinBridge(context));
        });
  }

  private static boolean supported(ParameterContext parameterContext) {
    return ViewContext.class.isAssignableFrom(parameterContext.getParameter().getType());
  }
}
