package io.zephyr.aire.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import io.aire.core.AireComponent;
import lombok.val;

@Tag("aire-overlay")
@JsModule("./components/aire/aire-overlay.ts")
@CssImport("./styles/aire/components/aire-overlay.css")
@NpmPackage(value = "lit-element", version = "^2.3.1")
public class AireOverlay extends Component implements AireComponent, HasComponents {

    private Component header;
    private Component content;
    private Component footer;
    private Component close;

    private String showClass = "aire-overlay-open";

    public AireOverlay() {
        setClose();
    }




    private void setClose() {
        val close = new Button();
        close.setIcon(new Icon(VaadinIcon.CLOSE_BIG));
        close.addClickListener(event -> close());
        close.getElement().setAttribute("slot", "close");
        if (this.close != null) {
            this.remove(this.close);
        }
        add(this.close = close);
    }

    public void setContent(Component content) {
        content.getElement().setAttribute("slot", "content");
        if (this.content != null) {
            this.remove(this.content);
        }
        add(this.content = content);
    }

    public void setHeader(Component header) {
        header.getElement().setAttribute("slot", "header");
        if (this.header != null) {
            this.remove(this.header);
        }
        add(this.header = header);
    }

    public void setFooter(Component footer) {
        footer.getElement().setAttribute("slot", "footer");
        if (this.footer != null) {
            this.remove(this.footer);
        }
        add(this.footer = footer);
    }

    public void close() {
        getElement().setAttribute("active", false);
        getElement().getClassList().set(showClass, false);
    }

    public void open(Component outer) {
        getElement().setAttribute("active", true);
        getElement().getClassList().set(showClass, true);

        val holder = outer.getElement();
        if (!holder.getStyle().has("position")) {
            holder.getStyle().set("position", "relative");
        }
        //should we actually be adding this to "outer"? Time will tell!

    }

}
