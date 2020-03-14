package io.zephyr.aire;

import com.vaadin.flow.router.*;
import io.zephyr.aire.api.ViewDecoratorManager;
import io.zephyr.aire.layout.AireApplicationViewport;
import com.vaadin.flow.component.dependency.CssImport;

import javax.inject.Inject;

@Route
@CssImport("./styles/aire/layout/aire-structure.css")
public class MainView extends AireApplicationViewport {

  @Inject
  public MainView(ViewDecoratorManager manager) {
    super(manager);
  }
}
