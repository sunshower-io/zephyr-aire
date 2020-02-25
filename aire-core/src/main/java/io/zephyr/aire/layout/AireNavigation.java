package io.zephyr.aire.layout;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Nav;

@CssImport("./styles/themes/aire/components/navigation.css")
public class AireNavigation extends Composite<Nav> implements Navigation {

  public AireNavigation() {
    getContent().add(new Button("Sup"));
  }
}
