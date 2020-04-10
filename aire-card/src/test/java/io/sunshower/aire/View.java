package io.sunshower.aire;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

@Route("")
public class View extends Div {

    public View() {
        AireCard paperSlider = new AireCard();
        add(paperSlider);
    }
}
