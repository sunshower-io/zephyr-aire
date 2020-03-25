package io.zephyr.admin.ui;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import io.zephyr.aire.designer.AireDesigner;
import io.zephyr.aire.layout.AireApplicationViewport;
import org.springframework.stereotype.Component;

@Component
@Route(value = "plugins", layout = AireApplicationViewport.class)
public class UploadPage extends VerticalLayout  {

  public UploadPage() {
    add(new AireDesigner());
    add(new H1("Upload!"));
  }
}
