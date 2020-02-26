package io.zephyr.aire.layout;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;

public class AireContent extends Composite<Div> {

  private final AirePanel panel;
  private final AireNavigation menu;

  public AireContent() {
    panel = new AirePanel();
    menu = new AireNavigation();
    getContent().add(menu);
    getContent().add(panel);
    getContent().add(new Button("frapper2"));
  }
}
