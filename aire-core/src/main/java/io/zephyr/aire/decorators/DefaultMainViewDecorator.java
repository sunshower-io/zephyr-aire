package io.zephyr.aire.decorators;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import io.zephyr.aire.api.Extension;
import io.zephyr.aire.api.ViewDecorator;
import io.zephyr.aire.layout.AireApplicationViewport;
import lombok.val;

@Extension(":ui:main")
public class DefaultMainViewDecorator implements ViewDecorator<AireApplicationViewport> {
  @Override
  public void decorate(AireApplicationViewport component) {
    val button = new Button();
    button.setIcon(new Image("/icons/icon.png", "Home"));
    component.getHeader().add(button);
  }
}
