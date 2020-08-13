package io.aire.designer;

public enum ImageTypes implements ImageType {
  Palette,
  Element;

  @Override
  public String getKey() {
    return this.name().toLowerCase();
  }
}
