package io.zephyr.admin.ui;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import io.zephyr.aire.layout.AireApplicationViewport;
import io.zephyr.kernel.core.Kernel;
import lombok.val;
import org.springframework.stereotype.Component;

import javax.inject.Inject;


@Component
@Route(value = "plugins", layout = AireApplicationViewport.class)
public class UploadPage extends VerticalLayout {


  @Inject
  public UploadPage(final Kernel kernel) {

    val plugins = kernel.getModuleManager().getModules();

    for (val plugin : plugins) {
      add(new H1(plugin.getCoordinate().getName()));
    }

    //    add(new AireDesigner());
    //    add(new H1("Upload!"));
  }
}
