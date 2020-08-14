package io.aire.designer;

import java.util.Collection;
import java.util.List;

public interface Element extends PropertyAware, Alignable {

  enum Category {
    Edge,
    Vertex;
  }

  String getId();

  void setId(String id);

  ElementType getType();

  void setType(ElementType type);

  Image addImage(Image image);

  Image removeImage(Image image);

  boolean hasImageWithRole(ImageType imageType);

  List<Image> getImages();

  Category getCategory();

  ElementStyle getStyle();

  Collection<ElementOverlay> getOverlays();
}
