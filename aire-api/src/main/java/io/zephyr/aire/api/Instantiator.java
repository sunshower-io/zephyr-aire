package io.zephyr.aire.api;

public interface Instantiator {

  <T> T instantiate(Class<T> type);
}
