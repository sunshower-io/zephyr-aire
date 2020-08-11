package io.aire.designer;

public interface Element {

  void setHorizontalAlignment(Alignment alignment);

  Alignment getHorizontalAlignment();


  void setVerticalAlignment(Alignment alignment);


  Alignment getVerticalAlignment();

  ElementStyle getStyle();

}
