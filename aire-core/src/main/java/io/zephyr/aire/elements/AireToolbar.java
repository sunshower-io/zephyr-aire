package io.zephyr.aire.elements;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

@Tag("aire-toolbar")
@CssImport("./styles/aire/components/aire-toolbar.css")
@JsModule("./components/aire/aire-toolbar.ts")
public class AireToolbar extends HorizontalLayout {

  public AireToolbar() {}
}
