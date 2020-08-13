package io.aire.designer;

public class Images {

  public static final Image palette(String url) {
    return palette(url, -1, -1);
  }

  public static final Image palette(String url, int width, int height) {
    return new Image(ImageTypes.Palette, url, width, height);
  }
}
