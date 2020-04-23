package io.zephyr.aire.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
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

  public void setIcon(Icon icon, String color) {
    this.icon.getStyle().set("background-color", color);
    icon.setColor("white");
    this.icon.add(icon);
    setIcon();
  }

  public void setIcon(Image icon, String color) {
    this.icon.getStyle().set("background-color", color);
    this.icon.add(icon);
    setIcon();
  }

  public void setIcon(Icon icon) {
    icon.setColor("white");
    this.icon.add(icon);
    setIcon();
  }

  public void setIcon(Image icon) {
    this.icon.add(icon);
    setIcon();
  }

  void setIcon() {
    this.icon.setClassName("aire-icon-holder");
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
