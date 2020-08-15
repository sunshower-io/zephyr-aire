package io.zephyr.aire.components.designer;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.DomEvent;

@DomEvent("model-changed")
public class ModelChangedEvent extends ComponentEvent<AireDesigner> {
  public ModelChangedEvent(AireDesigner source, boolean fromClient) {
    super(source, fromClient);
  }
}
