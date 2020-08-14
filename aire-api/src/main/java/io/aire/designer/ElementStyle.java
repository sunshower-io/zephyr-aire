package io.aire.designer;

import java.io.IOException;
import java.io.Writer;
import java.util.*;

public interface ElementStyle {

  Collection<String> directions =
      Set.of("DIRECTION_NORTH", "DIRECTION_SOUTH", "DIRECTION_EAST", "DIRECTION_WEST");

  Collection<String> alignments = Set.of("ALIGN_LEFT", "ALIGN_CENTER", "ALIGN_RIGHT");

  Collection<String> verticalAlignments = Set.of("ALIGN_TOP", "ALIGN_MIDDLE", "ALIGN_BOTTOM");

  Collection<String> arrows =
      Set.of(
          "ARROW_CLASSIC",
          "ARROW_CLASSIC_THIN",
          "ARROW_BLOCK",
          "ARROW_BLOCK_THIN",
          "ARROW_OPEN",
          "ARROW_OPEN_THIN",
          "ARROW_OVAL",
          "ARROW_DIAMOND",
          "ARROW_DIAMOND_THIN");

  StyleKey<String> Perimeter =
      new BaseStyleKey<>(
          "perimeter",
          StyleValidators.stringEnumerationValidator(
              "mxPerimeter.RectanglePerimeter",
              "mxPerimeter.EllipsePerimeter",
              "mxPerimeter.RhombusPerimeter",
              "mxPerimeter.TrianglePerimeter",
              "mxPerimeter.HexagonPerimeter")); // TODO determine if we can get rid of mxPerimeter;
  // add support for custom Perimeters

  StyleKey<String> SourcePort = new BaseStyleKey<>("sourcePort", StyleValidators.noop());

  StyleKey<String> TargetPort = new BaseStyleKey<>("targetPort", StyleValidators.noop());

  StyleKey<String> PortConstraint =
      new BaseStyleKey<>("portConstraint", StyleValidators.stringEnumerationValidator(directions));

  StyleKey<Integer> PortConstraintRotation =
      new BaseStyleKey<>(
          "portConstraintRotation", StyleValidators.integerEnumerationValidator(0, 1));

  StyleKey<String> SourcePortConstraint =
      new BaseStyleKey<>(
          "sourcePortConstraint", StyleValidators.stringEnumerationValidator(directions));

  StyleKey<String> TargetPortConstraint =
      new BaseStyleKey<>(
          "targetPortConstraint", StyleValidators.stringEnumerationValidator(directions));

  StyleKey<Integer> Opacity = new BaseStyleKey<>("opacity", StyleValidators.rangeValidator(0, 100));

  StyleKey<Integer> FillOpacity =
      new BaseStyleKey<>("fillOpacity", StyleValidators.rangeValidator(0, 100));

  StyleKey<Integer> StrokeOpacity =
      new BaseStyleKey<>("strokeOpacity", StyleValidators.rangeValidator(0, 100));

  StyleKey<Integer> TextOpacity =
      new BaseStyleKey<>("textOpacity", StyleValidators.rangeValidator(0, 100));

  StyleKey<String> TextDirection =
      new BaseStyleKey<>(
          "textDirection",
          StyleValidators.stringEnumerationValidator(
              "TEXT_DIRECTION_DEFAULT",
              "TEXT_DIRECTION_AUTO",
              "TEXT_DIRECTION_RTL",
              "TEXT_DIRECTION_LTR"));

  StyleKey<String> Overflow =
      new BaseStyleKey<>(
          "overflow",
          "visible",
          StyleValidators.stringEnumerationValidator("visible", "hidden", "fill", "width"));

  StyleKey<Boolean> Orthogonal = new BaseStyleKey<>("orthogonal", false, StyleValidators.noop());

  StyleKey<String> ExitX = new BaseStyleKey<>("exitX", StyleValidators.noop());

  StyleKey<String> ExitY = new BaseStyleKey<>("exitY", StyleValidators.noop());

  StyleKey<String> ExitDX = new BaseStyleKey<>("exitDX", StyleValidators.noop());

  StyleKey<String> ExitDY = new BaseStyleKey<>("exitDY", StyleValidators.noop());

  StyleKey<Integer> ExitPerimeter =
      new BaseStyleKey<>("exitPerimeter", 1, StyleValidators.integerEnumerationValidator(0, 1));

  StyleKey<String> EntryX = new BaseStyleKey<>("entryX", StyleValidators.noop());

  StyleKey<String> EntryY = new BaseStyleKey<>("entryY", StyleValidators.noop());

  StyleKey<String> EntryDX = new BaseStyleKey<>("entryDX", StyleValidators.noop());

  StyleKey<String> EntryDY = new BaseStyleKey<>("entryDY", StyleValidators.noop());

  StyleKey<Integer> EntryPerimeter =
      new BaseStyleKey<>("entryPerimeter", 1, StyleValidators.integerEnumerationValidator(0, 1));

  StyleKey<String> WhiteSpace =
      new BaseStyleKey<>(
          "whiteSpace", "nowrap", StyleValidators.stringEnumerationValidator("nowrap", "wrap"));

  StyleKey<Integer> Rotation =
      new BaseStyleKey<>("rotation", StyleValidators.rangeValidator(0, 360));

  StyleKey<String> FillColor = new BaseStyleKey<>("fillColor", StyleValidators.noop());

  StyleKey<Boolean> PointerEvents =
      new BaseStyleKey<>("pointerEvents", true, StyleValidators.noop());

  StyleKey<String> SwimlaneFillColor =
      new BaseStyleKey<>("swimlaneFillColor", StyleValidators.noop());

  StyleKey<Integer> Margin = new BaseStyleKey<>("margin", StyleValidators.minimumValidator(0));

  StyleKey<String> GradientColor = new BaseStyleKey<>("gradientColor", StyleValidators.noop());

  StyleKey<String> GradientDirection =
      new BaseStyleKey<>(
          "gradientDirection",
          "DIRECTION_SOUTH",
          StyleValidators.stringEnumerationValidator(directions));

  StyleKey<String> StrokeColor = new BaseStyleKey<>("strokeColor", StyleValidators.noop());

  StyleKey<String> SeparatorColor = new BaseStyleKey<>("separatorColor", StyleValidators.noop());

  StyleKey<Integer> StrokeWidth =
      new BaseStyleKey<>("strokeWidth", StyleValidators.minimumValidator(1));

  StyleKey<String> Align =
      new BaseStyleKey<>("align", StyleValidators.stringEnumerationValidator(alignments));

  StyleKey<String> VerticalAlign =
      new BaseStyleKey<>(
          "verticalAlign", StyleValidators.stringEnumerationValidator(verticalAlignments));

  StyleKey<String> LabelWidth = new BaseStyleKey<>("labelWidth", StyleValidators.noop());

  StyleKey<String> LabelPosition =
      new BaseStyleKey<>(
          "labelPosition", "ALIGN_CENTER", StyleValidators.stringEnumerationValidator(alignments));

  StyleKey<String> VerticalLabelPosition =
      new BaseStyleKey<>(
          "verticalLabelPosition",
          "ALIGN_MIDDLE",
          StyleValidators.stringEnumerationValidator(verticalAlignments));

  StyleKey<Integer> ImageAspect =
      new BaseStyleKey<>("imageAspect", 1, StyleValidators.integerEnumerationValidator(0, 1));

  StyleKey<String> ImageAlign =
      new BaseStyleKey<>("imageAlign", StyleValidators.stringEnumerationValidator(alignments));

  StyleKey<String> ImageVerticalAlign =
      new BaseStyleKey<>(
          "imageVerticalAlign", StyleValidators.stringEnumerationValidator(verticalAlignments));

  StyleKey<Integer> Glass =
      new BaseStyleKey<>("glass", 0, StyleValidators.integerEnumerationValidator(0, 1));

  StyleKey<String> Image = new BaseStyleKey<>("image", StyleValidators.noop());

  StyleKey<Integer> ImageWidth =
      new BaseStyleKey<>("imageWidth", StyleValidators.minimumValidator(1));

  StyleKey<Integer> ImageHeight =
      new BaseStyleKey<>("imageHeight", StyleValidators.minimumValidator(1));

  StyleKey<String> ImageBackground = new BaseStyleKey<>("imageBackground", StyleValidators.noop());

  StyleKey<String> ImageBorder = new BaseStyleKey<>("imageBorder", StyleValidators.noop());

  StyleKey<Integer> FlipH =
      new BaseStyleKey<>("flipH", 0, StyleValidators.integerEnumerationValidator(0, 1));

  StyleKey<Integer> FlipV =
      new BaseStyleKey<>("flipV", 0, StyleValidators.integerEnumerationValidator(0, 1));

  StyleKey<Boolean> NoLabel =
      new BaseStyleKey<>(
          "noLabel",
          false,
          StyleValidators.noop()); // docs confusing, this may actually be a 0|1 situation

  StyleKey<Boolean> NoEdgeStyle =
      new BaseStyleKey<>(
          "noEdgeStyle",
          false,
          StyleValidators.noop()); // docs confusing, this may actually be a 0|1 situation

  StyleKey<String> LabelBackgroundColor =
      new BaseStyleKey<>("labelBackgroundColor", StyleValidators.noop());

  StyleKey<String> LabelBorderColor =
      new BaseStyleKey<>("labelBorderColor", StyleValidators.noop());

  StyleKey<String> LabelPadding = new BaseStyleKey<>("labelPadding", StyleValidators.noop());

  // TODO IndicatorShape

  StyleKey<String> IndicatorImage = new BaseStyleKey<>("indicatorImage", StyleValidators.noop());

  StyleKey<String> IndicatorColor = new BaseStyleKey<>("indicatorColor", StyleValidators.noop());

  StyleKey<String> IndicatorStrokeColor =
      new BaseStyleKey<>("indicatorStrokeColor", StyleValidators.noop());

  StyleKey<String> IndicatorGradientColor =
      new BaseStyleKey<>("indicatorGradientColor", StyleValidators.noop());

  StyleKey<Integer> IndicatorSpacing =
      new BaseStyleKey<>("indicatorSpacing", StyleValidators.noop());

  StyleKey<Integer> IndicatorWidth =
      new BaseStyleKey<>("indicatorWidth", StyleValidators.minimumValidator(0));

  StyleKey<Integer> IndicatorHeight =
      new BaseStyleKey<>("indicatorHeight", StyleValidators.minimumValidator(0));

  StyleKey<String> IndicatorDirection =
      new BaseStyleKey<>(
          "gradientDirection",
          "DIRECTION_EAST",
          StyleValidators.stringEnumerationValidator(directions));

  StyleKey<Boolean> Shadow = new BaseStyleKey<>("shadow", StyleValidators.noop());

  StyleKey<Float> Segment = new BaseStyleKey<>("segment", StyleValidators.noop());

  StyleKey<String> EndArrow =
      new BaseStyleKey<>("endArrow", StyleValidators.stringEnumerationValidator(arrows));

  StyleKey<String> StartArrow =
      new BaseStyleKey<>("StartArrow", StyleValidators.stringEnumerationValidator(arrows));

  StyleKey<Integer> EndSize = new BaseStyleKey<>("endSize", StyleValidators.noop());

  StyleKey<Integer> StartSize = new BaseStyleKey<>("startSize", StyleValidators.noop());

  StyleKey<Integer> SwimlaneLine =
      new BaseStyleKey<>("swimlaneLine", 1, StyleValidators.integerEnumerationValidator(0, 1));

  StyleKey<Integer> EndFill =
      new BaseStyleKey<>("endFill", 1, StyleValidators.integerEnumerationValidator(0, 1));

  StyleKey<Integer> StartFill =
      new BaseStyleKey<>("startFill", 1, StyleValidators.integerEnumerationValidator(0, 1));

  String setStyle(String key, String value);

  String getStyle(String key);

  default String getStyle(StyleKey key) {
    return getStyle(key.getKey());
  }

  default <T> String setStyle(StyleKey<T> style, String value) {
    //    if (style.enumerable && !style.values.contains(value)) {
    //      throw new IllegalArgumentException(
    //          String.format(
    //              "Error: Style with key '%s' has permitted values %s, of which '%s' is not one",
    //              style.getKey(), style.values, value));
    //    }
    return setStyle(style.getKey(), value);
  }

  default ElementStyle set(String key, String value) {
    setStyle(key, value);
    return this;
  }

  default <T> ElementStyle set(StyleKey<T> key, String value) {
    setStyle(key, value);
    return this;
  }

  void write(Writer o) throws IOException;
}

final class DefaultElementStyle implements ElementStyle {

  final Map<String, String> styles;

  DefaultElementStyle() {
    // bit more memory, but these are usually small and order is important
    this.styles = new LinkedHashMap<>();
  }

  @Override
  public String setStyle(String key, String value) {
    return styles.put(key, value);
  }

  @Override
  public String getStyle(String key) {
    return styles.get(key);
  }

  @Override
  public void write(Writer o) throws IOException {
    o.write(toString());
  }

  @Override
  public String toString() {
    return styles.entrySet().stream()
        .reduce(
            new StringBuilder(),
            (r, e) -> r.append(e.getKey()).append("=").append(e.getValue()).append(";"),
            (a, b) -> a)
        .toString();
  }

  @Override
  public int hashCode() {
    return styles.hashCode();
  }

  @Override
  public boolean equals(Object o) {
    return o instanceof DefaultElementStyle && ((DefaultElementStyle) o).styles.equals(styles);
  }
}
