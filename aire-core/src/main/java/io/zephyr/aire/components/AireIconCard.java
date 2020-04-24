package io.zephyr.aire.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;

@Tag("aire-icon-card")
@JsModule("lit-element")
@JsModule("./components/aire/aire-icon-card.ts")
@CssImport("./styles/aire/components/aire-icon-card.css")
public class AireIconCard extends AireCard {

  private Component iconHolder;
  private Div icon;

  public AireIconCard() {
    super();
    this.icon = new Div();
  }

//  public void setIcon(Icon icon, String color) {
//    setColor(color);
//    setIcon(icon);
//  }
//
//  public void setIcon(Component icon, String color) {
//    setColor(color);
//    setIcon(icon);
//  }

  public void setIcon(Icon icon) {
    this.icon.add(icon);
    updateIcon();
  }

  public void setIcon(Component icon) {
    this.icon.addClassName("aire-icon-image-holder");
    this.icon.add(icon);
    updateIcon();
  }

  public void setColor(String color) {
    this.icon.getStyle().set("background-color", color);
  }

  void updateIcon() {
    this.icon.addClassName("aire-icon-holder");
    this.icon.getElement().setAttribute("slot", "icon");
    access(
        () -> {
          if (this.iconHolder != null) {
            this.remove(this.iconHolder);
          }
          add(this.iconHolder = this.icon);
        });
  }
}
