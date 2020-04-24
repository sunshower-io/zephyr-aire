package io.zephyr.aire.components;

import com.vaadin.flow.component.HasElement;

public enum AireVariant {
  Primary,
  Secondary,
  Tertiary,
  Success,
  Danger,
  Warning,
  Info,
  Link,
  Light,
  Dark;

  public void set(HasElement component) {
    component.getElement().getClassList().add(attribute());
  }

  public void unset(HasElement element) {
    element.getElement().getClassList().remove(attribute());
  }

  private String attribute() {
    return String.format("aire-%s", name().toLowerCase());
  }
}
