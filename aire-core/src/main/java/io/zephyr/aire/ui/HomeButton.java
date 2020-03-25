package io.zephyr.aire.ui;

import com.vaadin.flow.component.button.Button;
import io.zephyr.aire.api.Extension;
import io.zephyr.aire.api.Slot;
import io.zephyr.aire.layout.AireApplicationViewport;
import io.zephyr.aire.layout.Region;

@Slot(location = Region.Header.class, order = 0)
@Extension(target = AireApplicationViewport.class)
public class HomeButton extends Button {

  public HomeButton() {
    setText("Hello world");
  }
}
