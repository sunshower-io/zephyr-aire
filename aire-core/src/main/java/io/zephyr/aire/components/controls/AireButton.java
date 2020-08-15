package io.zephyr.aire.components.controls;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import lombok.val;

@CssImport("./styles/aire/layout/aire-button.css")
public class AireButton extends Composite<Button> {

  public AireButton() {
    getContent().addClassName("aire-button");
  }

  public AireButton(final String imageLocation, final String alt) {
    this();
    val image = new Image(imageLocation, alt);
    getContent().setIcon(image);
  }

  public AireButton(Icon icon) {
    this();
    getContent().setIcon(icon);
  }
}
