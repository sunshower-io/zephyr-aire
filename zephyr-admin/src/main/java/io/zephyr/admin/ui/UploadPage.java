package io.zephyr.admin.ui;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.router.Route;
import io.zephyr.aire.layout.AireApplicationViewport;
import org.springframework.stereotype.Component;

@Component
@Route(value = "plugins", layout = AireApplicationViewport.class)
public class UploadPage extends Div {

  public UploadPage() {
    add(new H1("Upload!"));
  }
}
