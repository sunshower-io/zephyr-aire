package io.zephyr.aire.designer;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.DomEvent;

@DomEvent("add-element")
public class AddElementEvent extends ComponentEvent<AirePalette> {
  public AddElementEvent(AirePalette source, boolean fromClient) {
    super(source, fromClient);
  }
}
