package io.zephyr.aire.api;

import com.vaadin.flow.component.HasElement;

@SuppressWarnings("unchecked")
public interface Positionable<T extends Positionable<T>> extends HasElement {

  default T apply(String position) {
    getElement().getClassList().add(position);
    return (T) this;
  }

  interface BottomPositionable<T extends BottomPositionable<T>> extends Positionable<T> {

    default T bottom() {
      return apply("bottom");
    }
  }

  interface TopPositionable<T extends TopPositionable<T>> extends Positionable<T> {

    default T top() {
      return apply("top");
    }
  }

  interface LeftPositionable<T extends LeftPositionable<T>> extends Positionable<T> {

    default T left() {
      return apply("left");
    }
  }

  interface RightPositionable<T extends RightPositionable<T>> extends Positionable<T> {

    default T right() {
      return apply("right");
    }
  }
}
