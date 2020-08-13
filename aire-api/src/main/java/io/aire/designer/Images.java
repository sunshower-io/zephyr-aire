package io.aire.designer;

public class Images {

  public static final Image palette(String url) {
    return palette(url, -1, -1);
  }

  public static final Image palette(String url, int width, int height) {
    return new Image(ImageTypes.Palette, url, width, height);
  }

  public static final Image element(String url, int width, int height) {
    return new Image(ImageTypes.Element, url, width, height);
  }

  public static Image element(String s) {
    return element(s, -1, -1);
  }
}
