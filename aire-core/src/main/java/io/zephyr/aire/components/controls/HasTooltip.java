package io.zephyr.aire.components.controls;

import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.dependency.CssImport;

@CssImport("./styles/aire/components/aire-has-tooltip.css")
public interface HasTooltip extends HasComponents {

    String TOOLTIP_PARENT_CLASS_NAME = "aire-has-tooltip";

    default void addTooltip(AireTooltip tooltip) {
        getElement().getClassList().add(TOOLTIP_PARENT_CLASS_NAME);
        add(tooltip);
    }
}
