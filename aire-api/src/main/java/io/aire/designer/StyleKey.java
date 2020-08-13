package io.aire.designer;

import java.util.Optional;

public interface StyleKey<T> {

  boolean hasDefaultValue();

  Optional<T> getDefaultValue();

  String getKey();

  void validate(T value);
}
