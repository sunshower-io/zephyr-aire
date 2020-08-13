package io.aire.designer;

public interface ElementOverlay extends Alignable {

  String getApplicableState();

  String getTooltip();

  Image getImage();

  Point<Float> getOffset();
}
