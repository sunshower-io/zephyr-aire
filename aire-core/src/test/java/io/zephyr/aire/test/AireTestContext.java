package io.zephyr.aire.test;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import lombok.val;

import java.util.Stack;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class AireTestContext {

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
}
