package io.zephyr.aire.reflect;

import lombok.val;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class ClassHierarchy implements Iterable<Class<?>> {

  private final Class<?> end;
  private final Class<?> start;

  public ClassHierarchy(Class<?> start, Class<?> end) {
    this.end = end;
    this.start = start;
  }

  public Iterable<Class<?>> reverse() {
    val result = new ArrayList<Class<?>>();
    for (val c : this) {
      result.add(c);
    }
    Collections.reverse(result);
    return result;
  }

  @Override
  public Iterator<Class<?>> iterator() {
    return new Iterator<>() {
      Class<?> current = start;

      @Override
      public boolean hasNext() {
        return current != end;
      }

      @Override
      public Class<?> next() {
        val result = current;
        current = current.getSuperclass();
        return result;
      }
    };
  }
}
