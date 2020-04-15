package io.zephyr.admin.ui;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import io.zephyr.aire.components.AireCard;
import io.zephyr.aire.layout.AireApplicationViewport;
import io.zephyr.kernel.core.Kernel;
import lombok.val;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
@CssImport("/modules/whatever/beans")
@Route(value = "plugins", layout = AireApplicationViewport.class)
public class PluginListPage extends VerticalLayout {

  @Inject
  public PluginListPage(final Kernel kernel) {
    this.setPadding(true);
    val plugins = kernel.getModuleManager().getModules();
    for (val plugin : plugins) {
      val card = new AireCard();
      val icon = VaadinIcon.QUESTION_CIRCLE_O;
      val header = new Icon(icon);
      card.setHeader(header);

      add(card);
    }
    UI.getCurrent().getPage().addStyleSheet("/modules/whatever/test");
    //    add(new AireDesigner());
    //    add(new H1("Upload!"));
  }
}
