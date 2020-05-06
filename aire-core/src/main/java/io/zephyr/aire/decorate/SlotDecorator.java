package io.zephyr.aire.decorate;

import io.zephyr.aire.api.Container;
import io.zephyr.aire.api.SlotRegistration;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.beans.Introspector;
import java.util.*;
import java.util.regex.Pattern;

@Slf4j
public class SlotDecorator {

  private final SlotRegistration slotRegistration;

  public SlotDecorator(SlotRegistration slot) {
    this.slotRegistration = slot;
  }

  public void uncache(Class<?> type) {
    Introspector.flushFromCaches(type);
  }

  public <T> T decorate(Class<T> type, T instance) {
    val container = type.getAnnotation(Container.class);

    return instance;
  }
}
