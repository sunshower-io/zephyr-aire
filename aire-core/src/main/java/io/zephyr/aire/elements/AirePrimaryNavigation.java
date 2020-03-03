package io.zephyr.aire.elements;

import com.vaadin.flow.component.html.Nav;

public class AirePrimaryNavigation extends AbstractAireContainer<Nav> {

  public AirePrimaryNavigation() {

    getContent().addClassNames("aire-nav", "primary");
  }
}
