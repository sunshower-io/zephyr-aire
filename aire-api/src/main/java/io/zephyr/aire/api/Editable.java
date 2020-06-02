package io.zephyr.aire.api;

public interface Editable<T> {
  boolean canApply(int operation);

  void apply(int operation, T instance);
}
