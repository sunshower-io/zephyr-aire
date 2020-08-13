package io.aire.designer;

public final class ElementStyles {

  private ElementStyles() {
    throw new AssertionError("No elementstyles for you!");
  }

  public static ElementStyle newStyle() {
    return new DefaultElementStyle();
  }

}
