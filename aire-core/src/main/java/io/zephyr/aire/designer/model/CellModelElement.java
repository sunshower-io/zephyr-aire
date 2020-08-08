package io.zephyr.aire.designer.model;

import lombok.Setter;

public class CellModelElement<T> implements ModelElement<T> {

  @Setter private String paletteIcon;

  @Override
  public String getPaletteIcon() {
    return paletteIcon;
  }
}
