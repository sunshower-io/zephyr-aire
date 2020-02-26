package io.zephyr.aire.layout;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Main;

@CssImport("./styles/themes/aire/components/viewport.css")
public class AireViewport extends Composite<Main> implements Viewport {

  private final AireHeader header;
  private final AireContent content;

  public AireViewport() {
    getContent().setClassName("aire-viewport");
    header = new AireHeader();
    content = new AireContent();
    getContent().add(header, content);

    getContent().add(new Button("hi"));
  }
}
