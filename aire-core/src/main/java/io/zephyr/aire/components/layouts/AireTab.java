package io.zephyr.aire.components.layouts;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.tabs.Tab;
import io.zephyr.aire.components.controls.HasTooltip;

import java.util.Objects;

public class AireTab extends Tab implements HasTooltip {

  public enum TabPlacement {
    /** place a tab after all tabs with a lower placement */
    END(3),

    /** place a tab in its insertion order */
    FLOW(2),
    /** place a tab after all tabs with a higher placement */
    START(1);

    final int value;

    TabPlacement(int value) {
      this.value = value;
    }
  }

  private boolean dirty;
  private TabPlacement placement = TabPlacement.FLOW;

  public AireTab() {
    this(TabPlacement.FLOW);
  }

  public AireTab(TabPlacement flow) {
    super();
    setPlacement(flow);
  }

  public AireTab(String label) {
    this(TabPlacement.FLOW, label);
  }

  public AireTab(TabPlacement placement, String label) {
    super(label);
    setPlacement(placement);
  }

  public AireTab(Component... components) {
    this(TabPlacement.FLOW, components);
  }

  public AireTab(TabPlacement placement, Component... components) {
    super(components);
    setPlacement(placement);
  }

  public void setDirty(boolean dirty) {
    this.dirty = dirty;
  }

  public boolean isDirty() {
    return this.dirty;
  }

  public TabPlacement getPlacement() {
    return placement;
  }

  public void setPlacement(TabPlacement placement) {
    Objects.requireNonNull(placement, "Tab Placement must not be null");
    this.placement = placement;
  }

  @Override
  public String toString() {
    return String.format("AireTab{placement=%s, dirty=%b}", placement, dirty);
  }
}
