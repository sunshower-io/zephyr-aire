package io.zephyr.aire.api;

public interface ViewContext extends Registration {
  Registration register(SlotRegistration slot);

  void close();
}
