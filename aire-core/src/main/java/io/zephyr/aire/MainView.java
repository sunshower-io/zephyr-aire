package io.zephyr.aire;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.router.*;
import io.zephyr.aire.api.Location;
import io.zephyr.aire.components.AireTabPane;
import io.zephyr.aire.layout.AireApplicationViewport;
import com.vaadin.flow.component.dependency.CssImport;
import lombok.val;

@Route
@Location("home")
@CssImport("./styles/aire/layout/aire-structure.css")
public class MainView extends AireApplicationViewport {

  public MainView() {

  }
}
