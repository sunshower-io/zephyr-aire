package io.zephyr.aire.decorators;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import io.zephyr.aire.api.Extension;
import io.zephyr.aire.api.ViewDecorator;
import io.zephyr.aire.api.ViewManager;
import io.zephyr.aire.elements.AireButton;
import io.zephyr.aire.layout.AireApplicationViewport;
import io.zephyr.aire.layout.TestRoute;
import lombok.val;

import javax.inject.Inject;

@Extension(":ui:main")
public class DefaultMainViewDecorator implements ViewDecorator<AireApplicationViewport> {

  @Inject private ViewManager viewManager;


  @Override
  public void decorate(AireApplicationViewport component) {
    addHomeButton(component);
    addFooterButton(component);
  }

  private void addFooterButton(AireApplicationViewport component) {
    val button = new AireButton(new Icon(VaadinIcon.QUESTION_CIRCLE_O));
    component.getFooter().add(button);


    viewManager.registerRoute(TestRoute.class);

    button
        .getContent()
        .addClickListener(
            t -> {
              UI.getCurrent().navigate("plugins");


            });
  }

  private void addHomeButton(AireApplicationViewport component) {

    val button = new AireButton("/icons/icon-dark.svg", "Home");
    button
        .getContent()
        .addClickListener(
            t -> {
              UI.getCurrent().navigate("");
            });

    component.getHeader().add(button);
  }
}
