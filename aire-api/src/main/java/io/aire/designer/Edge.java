package io.aire.designer;

public class Edge extends AbstractElement {

  public Edge() {
    this(ElementStyles.newStyle());
  }

  public Edge(ElementStyle elementStyle) {
    super(Category.Edge, elementStyle);
  }
}
