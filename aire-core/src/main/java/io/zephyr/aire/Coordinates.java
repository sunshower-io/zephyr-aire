package io.zephyr.aire;

import io.zephyr.kernel.Coordinate;

import java.util.regex.Pattern;

public class Coordinates {

  static final Pattern pattern = Pattern.compile("[[^-]&&[^\\w\\d]]");

  public static String toFragment(Coordinate coordinate) {
    return pattern.matcher(coordinate.toCanonicalForm()).replaceAll("/");
  }
}
