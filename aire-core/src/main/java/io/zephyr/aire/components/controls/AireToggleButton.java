package io.zephyr.aire.components.controls;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import io.zephyr.aire.components.layouts.AireUtilities;
import lombok.val;

@CssImport("./styles/aire/components/aire-toggle-button.css")
public class AireToggleButton extends Button {

    static final String CLASS_NAME = "aire-toggle-button";

    static final String ACTIVE_CLASS_NAME = "active";

    public AireToggleButton(Component icon) {
        super(icon);
        this.addClassName(CLASS_NAME);
        this.addClickListener(this::onClick);
    }

    private void onClick(ClickEvent<Button> event) {
        val button = event.getSource();
        AireUtilities.toggleClass(button, ACTIVE_CLASS_NAME);
    }

}
