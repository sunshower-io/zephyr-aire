package io.zephyr.aire.components.layouts;

import com.vaadin.flow.component.html.Section;
import io.zephyr.aire.components.layouts.AbstractAireContainer;

public class AirePanel extends AbstractAireContainer<Section> {

  public void clear() {
    removeAll();
  }
}
