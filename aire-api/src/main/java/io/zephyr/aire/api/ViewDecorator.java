package io.zephyr.aire.api;


public interface ViewDecorator {

  <T> T decorate(Class<T> type, T instance);
}
