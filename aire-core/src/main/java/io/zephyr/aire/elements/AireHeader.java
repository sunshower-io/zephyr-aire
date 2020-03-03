package io.zephyr.aire.elements;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Header;

@CssImport("./styles/aire/components/layout/aire-header.css")
public class AireHeader extends AbstractAireComposite<Header> {

  public AireHeader() {
    getContent().setClassName("aire-header");
  }

}
