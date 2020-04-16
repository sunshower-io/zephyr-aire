package io.zephyr.aire.api;

public interface LocationManager {

  void fireLocationChanged(String value);

  Iterable<String> getHierarchy();
}
