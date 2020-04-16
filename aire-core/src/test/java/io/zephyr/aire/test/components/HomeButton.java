package io.zephyr.aire.test.components;

import com.vaadin.flow.component.button.Button;
import io.zephyr.aire.MainView;
import io.zephyr.aire.api.Extension;
import io.zephyr.aire.api.ViewDecorator;

@Extension(targets = ":ui:main")
public class HomeButton implements ViewDecorator<MainView> {

  @Override
  public void decorate(MainView component) {
    component.setContent(new Button("Hello, world"));
  }
}
