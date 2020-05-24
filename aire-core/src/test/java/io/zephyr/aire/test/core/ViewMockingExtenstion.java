package io.zephyr.aire.test.core;

import com.github.mvysny.kaributesting.v10.MockVaadin;
import com.github.mvysny.kaributesting.v10.Routes;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.VaadinService;
import io.zephyr.aire.test.Element;
import io.zephyr.aire.test.ViewTest;
import lombok.val;
import org.junit.jupiter.api.extension.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static io.zephyr.aire.test.core.ApplicationTrackerListener.getContext;

public class ViewMockingExtenstion
    implements BeforeEachCallback, AfterEachCallback, ParameterResolver {

  @Override
  public boolean supportsParameter(
      ParameterContext parameterContext, ExtensionContext extensionContext)
      throws ParameterResolutionException {

    val parameter = parameterContext.getParameter();
    return parameter.isAnnotationPresent(Element.class);
  }

  @Override
  public Object resolveParameter(
      ParameterContext parameterContext, ExtensionContext extensionContext)
      throws ParameterResolutionException {
    val parameter = parameterContext.getParameter();
    val testContext = getContext().getBean(AireTestContext.class);
    return testContext.resolveFirst(parameter.getType());
  }

  @Override
  public void afterEach(ExtensionContext extensionContext) throws Exception {
    MockVaadin.tearDown();
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
    val routes = new Routes(results, Collections.emptySet(), false);
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
