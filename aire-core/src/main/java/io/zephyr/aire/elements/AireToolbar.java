package io.zephyr.aire.elements;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;

@Tag("aire-toolbar")
@JsModule("./components/aire/aire-toolbar.ts")
@CssImport("./styles/aire/components/aire-toolbar.css")
public class AireToolbar extends AbstractToolbarElement {

  public AireToolbar() {
    super();
  }
}
