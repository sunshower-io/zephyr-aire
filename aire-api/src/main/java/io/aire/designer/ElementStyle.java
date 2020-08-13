package io.aire.designer;

import java.io.IOException;
import java.io.Writer;
import java.util.*;

public interface ElementStyle {

  StyleKey<String> TextDirection =
      new BaseStyleKey<>(
          "textDirection", StyleValidators.stringEnumerationValidator("TEXT_DIRECTION_DEFAULT"));

  //  enum StyleKey {
  //    Perimeter("perimeter"),
  //    SourcePort("sourcePort"),
  //    TargetPort("targetPort"),
  //    PortConstraint("portConstraint"),
  //    PortConstraintRotation("portConstraintRotation"),
  //    SourcePortConstraint("sourcePortConstraint"),
  //    TargetPortConstraint("targetPortConstraint"),
  //    Opacity("opacity"),
  //    FillOpacity("fillOpacity"),
  //    StrokeOpacity("strokeOpacity"),
  //    TextOpacity("textOpacity"),
  //    TextDirection(
  //        "textDirection",
  //        Set.of(
  //            "TEXT_DIRECTION_DEFAULT",
  //            "TEXT_DIRECTION_AUTO",
  //            "TEXT_DIRECTION_RTL",
  //            "TEXT_DIRECTION_LTR"));
  //
  //    final String key;
  //    final boolean enumerable;
  //    final Set<String> values;
  //
  //    StyleKey(String key) {
  //      this(key, Collections.emptySet());
  //    }
  //
  //    StyleKey(String value, Set<String> values) {
  //      this.key = value;
  //      this.values = values;
  //      this.enumerable = !values.isEmpty();
  //    }
  //
  //    public String getKey() {
  //      return key;
  //    }
  //  }

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
