package io.zephyr.admin;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import io.zephyr.aire.layout.AireApplicationViewport;

@Route(value = "test", layout = AireApplicationViewport.class)
public class TestRoute extends VerticalLayout {

  public TestRoute() {

    add(new H1("henlo"));
    add(new H1("Wats the haps"));
  }
}
