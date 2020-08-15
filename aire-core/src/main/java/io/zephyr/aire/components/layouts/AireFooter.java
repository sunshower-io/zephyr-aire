package io.zephyr.aire.components.layouts;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Footer;
import io.zephyr.aire.components.layouts.AbstractAireContainer;

@CssImport("./styles/aire/layout/aire-footer.css")
public class AireFooter extends AbstractAireContainer<Footer> {

  public AireFooter() {
    getContent().addClassName("aire-footer");
  }
}
