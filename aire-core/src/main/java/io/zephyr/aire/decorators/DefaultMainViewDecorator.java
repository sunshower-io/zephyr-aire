package io.zephyr.aire.decorators;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.RouterLink;
import io.zephyr.aire.MainView;
import io.zephyr.aire.components.controls.AireButton;
import io.zephyr.aire.components.layouts.AireApplicationViewport;
import lombok.val;

public class DefaultMainViewDecorator {

  public void decorate(AireApplicationViewport component) {
    addHomeButton(component);
    addFooterButton(component);
  }

  private void addFooterButton(AireApplicationViewport component) {
    val button = new AireButton(new Icon(VaadinIcon.QUESTION_CIRCLE_O));
    component.getFooter().add(button);

    button
        .addClickListener(
            t -> {
              UI.getCurrent().navigate("plugins");
            });
  }

  private void addHomeButton(AireApplicationViewport component) {
    val routerLink = new RouterLink();
    routerLink.setRoute(MainView.class);
    routerLink.add(new Image("/icons/icon-dark.svg", "Home"));
    component.getHeader().add(routerLink);
  }
}
