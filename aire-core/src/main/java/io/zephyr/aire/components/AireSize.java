package io.zephyr.aire.components;

import com.vaadin.flow.component.HasElement;

public enum AireSize {
  Large,
  /** regular should be the default class (i.e. doesn't have large or small classes) */
  Small,
  Regular {
    public void set(HasElement el) {}

    public void clear(HasElement el) {}
  };

  public void set(HasElement element) {
    element.getElement().getClassList().add(name().toLowerCase());
  }

  public void clear(HasElement element) {
    element.getElement().getClassList().remove(name().toLowerCase());
  }
}
