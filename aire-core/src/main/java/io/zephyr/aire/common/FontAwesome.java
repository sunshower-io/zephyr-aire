package io.zephyr.aire.common;

import io.zephyr.aire.components.controls.AireIcon;

public enum FontAwesome {
  Plug;

  public AireIcon icon() {
    return new AireIcon(this.name());
  }
}
