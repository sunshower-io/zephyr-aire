package io.aire.designer;

import lombok.val;

import java.util.Objects;

public class Point<T extends Number> {

  final T x;
  final T y;

  public Point(T x, T y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }

  @Override
  @SuppressWarnings("unchecked")
  public boolean equals(Object o) {
    if (o == null) return false;
    if (o == this) return true;

    val op = (Point<T>) o;
    return x.equals(op.x) && y.equals(op.y);
  }

  @Override
  public String toString() {
    return String.format("(%s,%s)", x, y);
  }
}
