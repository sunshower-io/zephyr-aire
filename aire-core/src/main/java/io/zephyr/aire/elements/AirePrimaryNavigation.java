package io.zephyr.aire.elements;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Nav;

public class AirePrimaryNavigation extends AbstractAireComposite<Nav> {

  public AirePrimaryNavigation() {


    getContent().addClassNames("aire-nav", "primary");
  }


}
