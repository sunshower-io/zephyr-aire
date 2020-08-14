package io.aire.designer;

public interface PropertyAware {

  void setProperty(String key, String value);

  String getProperty(String key);

  boolean hasProperty(String key);
}
