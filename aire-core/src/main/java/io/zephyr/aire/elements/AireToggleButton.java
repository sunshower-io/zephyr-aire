package io.zephyr.aire.elements;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import lombok.val;

@CssImport("./styles/aire/components/aire-toggle-button.css")
public class AireToggleButton extends Button {

    static final String ACTIVE_CLASS_NAME = "active";

    public AireToggleButton(Component icon) {
        super(icon);
        this.addClassName("aire-toggle-button");
        this.addClickListener(this::onClick);
    }

    private void onClick(ClickEvent<Button> event) {
        val button = event.getSource();
        if (button.hasClassName(ACTIVE_CLASS_NAME)) {
            button.removeClassName(ACTIVE_CLASS_NAME);
        } else {
            button.addClassName(ACTIVE_CLASS_NAME);
        }
    }

}
