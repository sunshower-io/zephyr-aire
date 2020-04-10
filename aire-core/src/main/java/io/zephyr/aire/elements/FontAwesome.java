package io.zephyr.aire.elements;

public enum FontAwesome {
  Plug;

  public AireIcon icon() {
    return new AireIcon(this.name());
  }
}
