package io.aire.designer;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class AbstractElement implements Element {

  /** this map contains the set of images used by various designer components for this element */
  private Map<ImageType, Image> images;

  /** this map contains the set of overlays applied to this element */
  private List<ElementOverlay> overlays;

  @Override
  public Image addImage(Image image) {
    return null;
  }

  @Override
  public Image removeImage(Image image) {
    return null;
  }

  @Override
  public boolean hasImageWithRole(ImageType imageType) {
    return false;
  }

  @Override
  public List<Image> getImages() {
    return null;
  }

  @Override
  public Category getCategory() {
    return null;
  }

  @Override
  public ElementStyle getStyle() {
    return null;
  }

  @Override
  public Collection<ElementOverlay> getOverlays() {
    return null;
  }

  @Override
  public void setHorizontalAlignment(Alignment alignment) {}

  @Override
  public Alignment getHorizontalAlignment() {
    return null;
  }

  @Override
  public void setVerticalAlignment(Alignment alignment) {}

  @Override
  public Alignment getVerticalAlignment() {
    return null;
  }
}
