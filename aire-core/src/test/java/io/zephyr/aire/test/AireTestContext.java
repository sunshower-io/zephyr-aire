package io.zephyr.aire.test;

import com.github.mvysny.kaributesting.v10.MockVaadin;
import com.github.mvysny.kaributesting.v10.Routes;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.server.VaadinService;
import lombok.val;
import org.springframework.context.ApplicationContext;

import java.util.Stack;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class AireTestContext {

  final ApplicationContext applicationContext;

  public AireTestContext(final ApplicationContext context) {
    this.applicationContext = context;
  }

  public void withComponentRoute(Class<? extends Component> route, Runnable action) {
    val routes = new Routes().autoDiscoverViews(route.getPackageName());
    MockVaadin.setup(
        routes,
        (t, u) -> {
          val service = new TestVaadinService(t, u, applicationContext);
          VaadinService.setCurrent(service);
          return service;
        });

    try {
      action.run();
    } finally {
      MockVaadin.tearDown();
    }
  }

  public <T> T resolveFirst(Class<T> type) {
    return resolveFirst(t -> type.isAssignableFrom(t.getClass()));
  }

  public <T> T resolveFirst(Predicate<Component> test) {

    Stack<Component> c = new Stack<>();
    enqueue(UI.getCurrent().getChildren(), c);
    while (!c.isEmpty()) {
      val component = c.peek();
      if (test.test(component)) {
        return (T) component;
      } else {
        c.pop();
        enqueue(component.getChildren(), c);
      }
    }
    return null;
  }

  private void enqueue(Stream<Component> children, Stack<Component> c) {
    val childiter = children.iterator();
    while (childiter.hasNext()) {
      c.push(childiter.next());
    }
  }

  public void navigate(Class<? extends Component> type) {
    UI.getCurrent().navigate(type);
  }
}
