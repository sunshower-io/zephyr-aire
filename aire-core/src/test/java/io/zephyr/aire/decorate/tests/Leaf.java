package io.zephyr.aire.decorate.tests;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import io.zephyr.aire.api.Slot;
import lombok.Getter;

public class Leaf extends Composite<Div> {
  @Getter
  @Slot(":frapper")
  Button leafButton;

  public void setLeafButton(Button leafButton) {
    this.leafButton = leafButton;
    getElement().appendChild(leafButton.getElement());
  }
}
