package io.zephyr.aire.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.tabs.Tabs;
import lombok.val;

import java.util.stream.IntStream;

public class AireTabs extends Tabs {

  public void add(Component... components) {
    for (val component : components) {
      if (component instanceof AireTab) {
        addPositionedTab((AireTab) component);
      } else {
        insertFlowComponent(component);
      }
    }
  }

  private void addPositionedTab(AireTab component) {
    switch (component.getPlacement()) {
      case START:
        prependPositionedTab(component);
        break;
      case END:
        appendPositionedTab(component);
        break;
      case FLOW:
        insertFlowComponent(component);
    }
  }

  private void insertFlowComponent(Component component) {
    int p = IntStream.range(0, getComponentCount()).filter(this::isEnd).findFirst().orElse(-1);
    if (p == -1) {
      super.add(component);
    } else {
      super.addComponentAtIndex(p, component);
    }
  }

  private boolean isEnd(int i) {
    val child = getComponentAt(i);
    return child instanceof AireTab && ((AireTab) child).getPlacement() == AireTab.TabPlacement.END;
  }

  private void appendPositionedTab(AireTab component) {
    super.addComponentAtIndex(getComponentCount(), component);
  }

  private void prependPositionedTab(AireTab component) {
    this.addComponentAtIndex(0, component);
  }
}
