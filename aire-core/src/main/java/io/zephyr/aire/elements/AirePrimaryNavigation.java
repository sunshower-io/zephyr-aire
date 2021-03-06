package io.zephyr.aire.elements;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Nav;

@CssImport("./styles/aire/layout/aire-primary-navigation.css")
public class AirePrimaryNavigation extends AbstractAireContainer<Nav> {

  public AirePrimaryNavigation() {

    getContent().addClassNames("aire-nav", "primary");
  }
}
