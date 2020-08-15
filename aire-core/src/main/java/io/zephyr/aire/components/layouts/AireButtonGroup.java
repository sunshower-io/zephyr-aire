package io.zephyr.aire.components.layouts;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Nav;
import io.aire.core.AireComponent;

@CssImport("./styles/aire/components/aire-button-group.css")
public class AireButtonGroup extends Nav implements AireComponent {

  public AireButtonGroup() {
    addClassName("aire-button-group");
  }

  public void add(Button button) {
    super.add(button);
  }

  public void remove(Button button) {
    super.remove(button);
  }
}
