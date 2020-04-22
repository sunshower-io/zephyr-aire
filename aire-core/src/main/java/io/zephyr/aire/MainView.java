package io.zephyr.aire;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.*;
import io.zephyr.aire.api.Location;
import io.zephyr.aire.components.AireAsideDrawerMenu;
import io.zephyr.aire.components.AireIconCard;
import io.zephyr.aire.layout.AireApplicationViewport;
import com.vaadin.flow.component.dependency.CssImport;
import lombok.val;

@Route
@Location("home")
@CssImport("./styles/aire/layout/aire-structure.css")
public class MainView extends AireApplicationViewport {

  public MainView() {
    val drawer = new AireAsideDrawerMenu();
    val fst = new Button();
    fst.setIcon(new Icon(VaadinIcon.INVOICE));
    drawer.add(fst, new Test());

    val snd = new Button();
    snd.setIcon(new Icon(VaadinIcon.AIRPLANE));
    drawer.add(snd, new Button("frapper"));
    setSecondaryNavigation(drawer);

    val holder = new Div();
    val iconCard = new AireIconCard();
    iconCard.setIcon(new Icon(VaadinIcon.AIRPLANE));
    iconCard.setHeader(new H1("No Flights For Us"));
    iconCard.setFooter(new Span("foot me"));
    iconCard.setContent(new Paragraph("Lorem ipsum dolor sit amet"));
    holder.add(iconCard);
    val h1 = new H1("Lisa testing");
    holder.add(h1);
    setContent(holder);
  }
}
