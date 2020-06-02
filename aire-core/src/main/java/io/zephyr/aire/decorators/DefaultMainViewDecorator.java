package io.zephyr.aire.decorators;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.RouterLink;
import io.zephyr.aire.MainView;
import io.zephyr.aire.api.Extension;
import io.zephyr.aire.api.LocationManager;
import io.zephyr.aire.api.ViewDecorator;
import io.zephyr.aire.api.ViewManager;
import io.zephyr.aire.elements.AireButton;
import io.zephyr.aire.elements.BreadCrumb;
import io.zephyr.aire.layout.AireApplicationViewport;
import lombok.val;

import javax.inject.Inject;

public class DefaultMainViewDecorator {

  public void decorate(AireApplicationViewport component) {
    addHomeButton(component);
    addFooterButton(component);
  }

  private void addFooterButton(AireApplicationViewport component) {
    val button = new AireButton(new Icon(VaadinIcon.QUESTION_CIRCLE_O));
    component.getFooter().add(button);

    button
        .getContent()
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
