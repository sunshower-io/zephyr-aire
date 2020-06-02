package io.zephyr.aire.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;

@Tag("aire-media-card")
@JsModule("lit-element")
@JsModule("./components/aire/aire-media-card.ts")
@CssImport("./styles/aire/components/aire-media-card.css")
public class AireMediaCard extends AireCard {

    private Component media;

    public AireMediaCard() {
        super();
    }

    public void setMedia(Component media) {
        media.getElement().setAttribute("slot", "media");
        UI.getCurrent()
            .access(
                    () -> {
                        if (this.media != null) {
                            this.remove(this.media);
                        }
                        add(this.media = media);
                    });
    }
}
