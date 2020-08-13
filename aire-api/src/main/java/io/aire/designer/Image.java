package io.aire.designer;

public class Image {

  private int width;
  private int height;
  private String source;

  public Image(String source, int width, int height) {
    this.width = width;
    this.height = height;
    this.source = source;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public String getSource() {
    return source;
  }
}
