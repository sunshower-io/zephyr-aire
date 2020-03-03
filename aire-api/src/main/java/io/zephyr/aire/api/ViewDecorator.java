package io.zephyr.aire.api;

public interface ViewDecorator<T> {
  void decorate(T value, Session session);


}
