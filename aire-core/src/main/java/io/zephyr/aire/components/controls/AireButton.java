package io.zephyr.aire.components.controls;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import lombok.val;

@CssImport("./styles/aire/layout/aire-button.css")
@Tag("aire-button")
public class AireButton extends Button implements HasTooltip {

  public AireButton() {
    addClassName("aire-button");
  }

  public AireButton(final String imageLocation, final String alt) {
    this();
    val image = new Image(imageLocation, alt);
    setIcon(image);
  }

  public AireButton(Icon icon) {
    this();
    setIcon(icon);
  }

  @Override
  public void remove(Component... components) {
    super.remove(components);
  }

  @Override
  public void removeAll() {
    super.removeAll();
  }
}
