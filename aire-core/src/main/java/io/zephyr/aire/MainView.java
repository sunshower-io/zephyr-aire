package io.zephyr.aire;

import com.vaadin.flow.router.*;
import io.zephyr.aire.api.Session;
import io.zephyr.aire.api.ViewDecoratorManager;
import io.zephyr.aire.api.ViewManager;
import io.zephyr.aire.layout.AireApplicationViewport;
import io.zephyr.kernel.core.Kernel;
import com.vaadin.flow.component.dependency.CssImport;

import javax.inject.Inject;

@Route
@CssImport("./styles/aire/components/layout/aire-structure.css")
public class MainView extends AireApplicationViewport {

  static final String key = "aire.views.primary";

  @Inject
  public MainView(final Kernel kernel, ViewDecoratorManager manager) {
    super();
    manager.decorate(key, this);
  }
}
