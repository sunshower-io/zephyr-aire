package io.zephyr.aire.api;

import java.util.Collection;
import java.util.Set;

public class SlotRegistration {

  final Class<?> component;
  final Set<String> targetSlots;

  public SlotRegistration(Class<?> component, Collection<? extends String> targets) {
    this.component = component;
    this.targetSlots = Set.copyOf(targets);
  }
}
