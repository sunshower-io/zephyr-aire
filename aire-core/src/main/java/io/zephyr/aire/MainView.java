package io.zephyr.aire;

import com.vaadin.flow.router.*;
import io.zephyr.aire.api.ExtensionPoint;
import io.zephyr.aire.layout.AireApplicationViewport;
import com.vaadin.flow.component.dependency.CssImport;


@Route
@ExtensionPoint("test")
@CssImport("./styles/aire/layout/aire-structure.css")
public class MainView extends AireApplicationViewport {

  public MainView() {
  }
}
