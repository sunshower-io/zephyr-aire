package io.aire.designer;

public class Vertex extends AbstractElement {

  public Vertex() {
    this(ElementStyles.newStyle());
  }

  public Vertex(ElementStyle elementStyle) {
    super(Category.Vertex, elementStyle);
  }
}
