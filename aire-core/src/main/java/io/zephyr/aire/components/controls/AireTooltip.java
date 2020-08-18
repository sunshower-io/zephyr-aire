package io.zephyr.aire.components.controls;

import com.vaadin.flow.component.HtmlContainer;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.CssImport;

@Tag("aire-tooltip")
@CssImport("./styles/aire/components/aire-tooltip.css")
public class AireTooltip extends HtmlContainer {

    public enum Horizontal {
        RIGHT(AireTooltip.HORIZONTAL_RIGHT),
        LEFT(AireTooltip.HORIZONTAL_LEFT),
        MIDDLE(AireTooltip.HORIZONTAL_MIDDLE);

        private final String value;

        Horizontal(String value) {
            this.value = value;
        }

    }

    public enum Vertical {
        TOP(AireTooltip.VERTICAL_TOP),
        BOTTOM(AireTooltip.VERTICAL_BOTTOM),
        MIDDLE(AireTooltip.VERTICAL_MIDDLE);

        private final String value;

        Vertical(String value) {
            this.value = value;
        }

    }

    static final String HORIZONTAL_RIGHT = "aire-tooltip-horizontal-right";
    static final String HORIZONTAL_LEFT = "aire-tooltip-horizontal-left";
    static final String HORIZONTAL_MIDDLE = "aire-tooltip-horizontal-middle";
    static final String VERTICAL_BOTTOM = "aire-tooltip-vertical-bottom";
    static final String VERTICAL_TOP = "aire-tooltip-vertical-top";
    static final String VERTICAL_MIDDLE = "aire-tooltip-vertical-middle";

    public AireTooltip() {
        setPosition(Horizontal.RIGHT, Vertical.BOTTOM);
    }

    public AireTooltip(String text) {
        this();
        setText(text);
    }

    public void setHorizontalPosition(Horizontal direction) {
        removeClassNames(HORIZONTAL_RIGHT, HORIZONTAL_LEFT, HORIZONTAL_MIDDLE);
        addClassName(direction.value);
    }

    public void setVerticalPosition(Vertical direction) {
        removeClassNames(VERTICAL_TOP, VERTICAL_BOTTOM, VERTICAL_MIDDLE);
        addClassName(direction.value);
    }

    public void setPosition(Horizontal horizontal, Vertical vertical) {
        setHorizontalPosition(horizontal);
        setVerticalPosition(vertical);
    }
}
