package io.sunshower.aire;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;
import io.sunshower.aire.designer.Designer;

@Route("")
public class View extends Div {

  public View() {
    add(new Designer());
  }
}
