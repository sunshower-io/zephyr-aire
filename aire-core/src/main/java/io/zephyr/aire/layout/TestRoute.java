package io.zephyr.aire.layout;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Article;
import com.vaadin.flow.router.Route;

@Route(value = "plugins", layout = AireApplicationViewport.class)
public class TestRoute extends Article {

  public TestRoute() {
    add(new Button("Hello, wabbington"));
  }
}
