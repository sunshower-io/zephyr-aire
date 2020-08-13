package io.aire.designer;

import java.util.Collection;
import java.util.List;

public interface Element extends Alignable {

  enum Category {
    Edge,
    Vertex
  }

  Image addImage(Image image);

  Image removeImage(Image image);

  boolean hasImageWithRole(ImageType imageType);

  List<Image> getImages();

  Category getCategory();

  ElementStyle getStyle();


  Collection<ElementOverlay> getOverlays();
}
