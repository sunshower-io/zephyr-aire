package io.aire.designer;

import java.util.Optional;

public class BaseStyleKey<T> implements StyleKey<T> {

  final String key;
  final Optional<T> defaultValue;
  final StyleValidator<T> validator;

  public BaseStyleKey(final String key, final T value, final StyleValidator<T> validator) {
    this(key, Optional.of(value), validator);
  }

  public BaseStyleKey(final String key, final StyleValidator<T> validator) {
    this(key, Optional.empty(), validator);
  }

  protected BaseStyleKey(
      final String key, final Optional<T> defaultValue, final StyleValidator<T> validator) {
    this.key = key;
    this.validator = validator;
    this.defaultValue = defaultValue;
  }

  @Override
  public boolean hasDefaultValue() {
    return defaultValue.isPresent();
  }

  @Override
  public Optional<T> getDefaultValue() {
    return defaultValue;
  }

  @Override
  public String getKey() {
    return key;
  }

  @Override
  public void validate(T value) {
    if (!validator.validate(value)) {
      throw new IllegalArgumentException(
          String.format("Error:  style with key '%s' cannot have value '%s'", key, value));
    }
  }
}
