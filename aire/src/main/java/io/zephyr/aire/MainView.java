package io.zephyr.aire;

import com.vaadin.flow.router.*;
import io.zephyr.aire.layout.AireViewport;
import io.zephyr.kernel.core.Kernel;
import com.vaadin.flow.component.dependency.CssImport;

import javax.inject.Inject;

@Route
@CssImport("./styles/themes/aire/theme.css")
public class MainView extends AireViewport {

  @Inject
  public MainView(GreetService service, final Kernel kernel) {
    super();
  }
}
