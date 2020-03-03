package io.zephyr.aire.api;

public interface ViewDecoratorManager {
  <T> void decorate(T view);

  <T> void decorate(String key, T view);
}
