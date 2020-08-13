package io.aire.designer;

import java.util.Set;

public class StyleValidators {

  static final <T> StyleValidator<T> noop() {
    return value -> true;
  }

  static final StyleValidator<String> stringEnumerationValidator(String... values) {
    return value -> Set.of(values).contains(value);
  }

  static final <T extends Number & Comparable<T>> StyleValidator<T> rangeValidator(T min, T max) {
    return value -> min.compareTo(value) <= 0 && max.compareTo(value) >= 0;
  }
}
