package io.zephyr.aire.test.core;

import com.github.mvysny.kaributesting.v10.MockVaadin;
import com.github.mvysny.kaributesting.v10.Routes;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.VaadinService;
import io.zephyr.aire.servlet.AireVaadinServlet;
import io.zephyr.aire.test.xpath.ElementXPathMatcher;
import lombok.val;
import org.springframework.context.ApplicationContext;

import java.util.*;
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
          AireVaadinServlet.setInstance(t);
          VaadinService.setCurrent(service);
          return service;
        });

    try {
      action.run();
    } finally {
      MockVaadin.tearDown();
    }
  }

  public Collection<? extends HasElement> resolveAll(Class<?> type) {
    return resolveAll(t -> type.isAssignableFrom(t.getClass()));
  }

  public List<? extends HasElement> resolveAll(Predicate<HasElement> predicate) {
    val c = new Stack<Component>();
    val results = new ArrayList<HasElement>();
    enqueue(UI.getCurrent().getChildren(), c);
    while (!c.isEmpty()) {
      val next = c.pop();
      if (predicate.test(next)) {
        results.add(next);
      }
      enqueue(next.getChildren(), c);
    }
    return results;
  }

  public HasElement resolveFirstAtPath(String path) {

    val component = UI.getCurrent().getElement().getComponent();
    if (component.isPresent()) {
      val compiler = new ElementXPathMatcher(path);
      val results = compiler.select(component.get());
      return results;
    }
    return null;
  }

  public List<? extends HasElement> resolveAtPath(String path) {

    val component = UI.getCurrent().getElement().getComponent();
    if (component.isPresent()) {
      val compiler = new ElementXPathMatcher(path);
      return compiler.match(component.get());
    }
    return Collections.emptyList();
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
