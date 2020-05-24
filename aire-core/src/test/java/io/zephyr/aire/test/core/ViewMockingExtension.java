package io.zephyr.aire.test.core;

import com.github.mvysny.kaributesting.v10.MockVaadin;
import com.github.mvysny.kaributesting.v10.Routes;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinServletService;
import io.zephyr.aire.test.Element;
import io.zephyr.aire.test.Elements;
import io.zephyr.aire.test.ViewTest;
import lombok.val;
import org.junit.jupiter.api.extension.*;

import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.util.*;

import static io.zephyr.aire.test.core.ApplicationTrackerListener.getContext;

public class ViewMockingExtension
    implements BeforeEachCallback, AfterEachCallback, ParameterResolver {

  @Override
  public boolean supportsParameter(
      ParameterContext parameterContext, ExtensionContext extensionContext)
      throws ParameterResolutionException {

    val parameter = parameterContext.getParameter();
    return parameter.isAnnotationPresent(Element.class)
        || parameter.isAnnotationPresent(Elements.class);
  }

  @Override
  public Object resolveParameter(
      ParameterContext parameterContext, ExtensionContext extensionContext)
      throws ParameterResolutionException {
    val parameter = parameterContext.getParameter();
    val testContext = getContext().getBean(AireTestContext.class);

    if (parameter.isAnnotationPresent(Element.class)) {
      return testContext.resolveFirst(parameter.getType());
    }

    if (parameter.isAnnotationPresent(Elements.class)) {
      val type = parameter.getType();

      if (Set.class.isAssignableFrom(type)) {
        return new HashSet<>(testContext.resolveAll(typeOf(parameter)));
      }
      if (List.class.isAssignableFrom(type)) {
        return testContext.resolveAll(typeOf(parameter));
      }
    }

    throw new IllegalArgumentException("Can't process parameter " + parameter);
  }

  static Class<?> typeOf(Parameter type) {
    val ptype = (ParameterizedType) type.getParameterizedType();
    return (Class<?>) ptype.getActualTypeArguments()[0];
  }

  @Override
  public void afterEach(ExtensionContext extensionContext) throws Exception {
    UI.setCurrent(null);
    MockVaadin.tearDown();
    VaadinService.setCurrent(null);
  }

  @Override
  public void beforeEach(ExtensionContext extensionContext) throws Exception {
    setUp(extensionContext);
    activate(extensionContext);
  }

  private void activate(ExtensionContext extensionContext) {
    var viewTest = extensionContext.getRequiredTestMethod().getAnnotation(ViewTest.class);
    if (doActivate(viewTest)) {
      return;
    }
    viewTest = extensionContext.getRequiredTestClass().getAnnotation(ViewTest.class);
    doActivate(viewTest);
  }

  private boolean doActivate(ViewTest viewTest) {
    if (!(viewTest == null || viewTest.active().equals(Component.class))) {
      UI.getCurrent().navigate(viewTest.active());
      return true;
    }
    return false;
  }

  private Routes setUp(ExtensionContext extensionContext) {
    val results = new HashSet<Class<? extends Component>>();
    val testClass = extensionContext.getRequiredTestClass();
    var viewTest = testClass.getAnnotation(ViewTest.class);

    collectRoutes(results, viewTest);

    viewTest = extensionContext.getRequiredTestMethod().getAnnotation(ViewTest.class);
    collectRoutes(results, viewTest);

    val context = getContext();
    val routes = new Routes(results, Collections.emptySet(), true);

    MockVaadin.setup(
        routes,
        (t, u) -> {
          val svc = new TestVaadinService(t, u, context);
          VaadinService.setCurrent(svc);
          return svc;
        });

    return routes;
  }

  private void collectRoutes(Set<Class<? extends Component>> results, ViewTest viewTest) {
    if (viewTest != null) {
      for (val type : viewTest.value()) {
        results.add(type);
      }
    }
  }
}
