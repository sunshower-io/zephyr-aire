package io.zephyr.aire.api;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

public class SlotRegistration {

  private final Slot.Mode mode;
  private final Class<?> component;
  private final Set<String> targetSlots;

  public SlotRegistration(Class<?> component, Collection<? extends String> targets) {
    this(component, Slot.Mode.APPEND, targets);
  }

  public SlotRegistration(
      Class<?> component, Slot.Mode mode, Collection<? extends String> targets) {
    this.mode = mode;
    this.component = component;
    this.targetSlots = Set.copyOf(targets);
  }

  public Set<String> getTargets() {
    return Collections.unmodifiableSet(targetSlots);
  }
}
