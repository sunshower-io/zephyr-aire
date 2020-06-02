package io.zephyr.aire.api;


public interface Operation<T> {

  void apply(T field, Instantiator instantiator);
}
