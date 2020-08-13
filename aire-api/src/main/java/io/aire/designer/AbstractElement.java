package io.aire.designer;

import java.util.*;

public class AbstractElement implements Element {

  /**
   * encapsulated state:
   *
   * <p>each of these fields is modifiable via the relevant accessors
   */
  private Alignment verticalAlignment;

  private Alignment horizontalAlignment;

  /**
   * inherited state:
   *
   * <p>each of these fields should be accessible, yet unmodifiable except via the underlying data
   * structures to subclasses of @AbstractElement
   */
  protected final ElementStyle style;
  /** what type of element are we? */
  protected final Category category;

  /** this map contains the set of images used by various designer components for this element */
  protected final Map<ImageType, Image> images;

  /** this map contains the set of overlays applied to this element */
  protected final List<ElementOverlay> overlays;

  protected AbstractElement(Category category) {
    this(category, ElementStyles.newStyle());
  }

  protected AbstractElement(Category category, ElementStyle style) {
    this.style = style;
    this.category = category;
    this.images = defaultImages();
    this.overlays = defaultOverlays();
  }

  @Override
  public Image addImage(Image image) {
    return images.put(image.getType(), image);
  }

  @Override
  public Image removeImage(Image image) {
    return images.remove(image.getType());
  }

  @Override
  public boolean hasImageWithRole(ImageType imageType) {
    return images.containsKey(imageType);
  }

  @Override
  public List<Image> getImages() {
    return List.copyOf(images.values());
  }

  @Override
  public Category getCategory() {
    return category;
  }

  @Override
  public ElementStyle getStyle() {
    return style;
  }

  @Override
  public Collection<ElementOverlay> getOverlays() {
    return null;
  }

  @Override
  public void setHorizontalAlignment(Alignment alignment) {
    this.horizontalAlignment = alignment;
  }

  @Override
  public Alignment getHorizontalAlignment() {
    return horizontalAlignment;
  }

  @Override
  public void setVerticalAlignment(Alignment alignment) {
    this.verticalAlignment = alignment;
  }

  @Override
  public Alignment getVerticalAlignment() {
    return verticalAlignment;
  }

  protected List<ElementOverlay> defaultOverlays() {
    return new ArrayList<>();
  }

  private Map<ImageType, Image> defaultImages() {
    return new HashMap<>();
  }
}
