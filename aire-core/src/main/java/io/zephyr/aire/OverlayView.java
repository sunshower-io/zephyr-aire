package io.zephyr.aire;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import io.zephyr.aire.components.AireOverlay;
import lombok.val;

@PageTitle("Overlay")
@Route(value="overlay", layout=MainView.class)
public class OverlayView extends AireOverlay {

    public OverlayView() {
        setHeader(new H1("heck"));
        setContent(new Paragraph("Lorem ipsum dolor sit amet."));

        val footerButton = new Button();
        footerButton.setText("Cancel");
        footerButton.addClickListener(
                (ComponentEventListener<ClickEvent<Button>>)
                        overlayClickEvent -> close());
        setFooter(footerButton);

        open();
    }

}
