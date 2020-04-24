package io.zephyr.aire.components;

import com.vaadin.flow.component.HasElement;

public enum AireVariant {
  Primary,
  Secondary,
  Success,
  Danger,
  Warning,
  Info,
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
