package io.zephyr.admin;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.Route;
import io.zephyr.aire.layout.AireApplicationViewport;

@Route(value = "test2", layout = AireApplicationViewport.class)
public class TestRoute2 extends VerticalLayout {

  public TestRoute2() {

    add(new H1("frapper"));
    add(new H1("beansrus"));
  }
}
