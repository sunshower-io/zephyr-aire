package io.zephyr.aire;

import com.vaadin.flow.router.*;
import io.zephyr.aire.api.Location;
import io.zephyr.aire.elements.AireIcon;
import io.zephyr.aire.layout.AireApplicationViewport;
import com.vaadin.flow.component.dependency.CssImport;

@Route
@Location("home")
@CssImport("./styles/aire/layout/aire-structure.css")
public class MainView extends AireApplicationViewport {

  public MainView() {

  }
}
