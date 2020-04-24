package io.zephyr.aire.elements;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Footer;

@CssImport("./styles/aire/layout/aire-footer.css")
public class AireFooter extends AbstractAireContainer<Footer> {

  public AireFooter() {
    getContent().addClassName("aire-footer");
  }
}
