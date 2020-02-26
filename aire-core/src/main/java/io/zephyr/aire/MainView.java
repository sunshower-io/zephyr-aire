package io.zephyr.aire;

import com.vaadin.flow.router.*;
import io.zephyr.aire.layout.AireApplicationViewport;
import io.zephyr.kernel.core.Kernel;
import com.vaadin.flow.component.dependency.CssImport;

import javax.inject.Inject;

@Route
@CssImport("./styles/aire/components/layout/aire-structure.css")
public class MainView extends AireApplicationViewport {

  @Inject
  public MainView(final Kernel kernel) {
    super();
  }
}
