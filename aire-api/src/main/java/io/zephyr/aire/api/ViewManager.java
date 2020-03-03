package io.zephyr.aire.api;

public interface ViewManager {

  void register(Class<?> type);

  <T> ViewDecoratorRegistration register(Class<T> viewType, ViewDecorator<T> decorator);

  <T> ViewDecoratorRegistration register(String key, ViewDecorator<T> decorator);
}
