package io.zephyr.aire.components.layouts;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Header;
import io.zephyr.aire.components.layouts.AbstractAireContainer;

@CssImport("./styles/aire/layout/aire-header.css")
public class AireHeader extends AbstractAireContainer<Header> {

  public AireHeader() {
    getContent().setClassName("aire-header");
  }

}
