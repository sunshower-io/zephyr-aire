package io.zephyr.aire.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import io.zephyr.aire.elements.AirePanel;
import lombok.val;

@Tag("aire-card")
@CssImport("./styles/aire/components/aire-card.css")
@JsModule("./components/aire/aire-card.ts")
@JsModule("lit-element")
@NpmPackage(value = "lit-element", version = "^2.3.1")
public class AireCard extends Component implements HasComponents {

  private AirePanel content;
  private Component footer;
  private Component header;

  public AireCard() {
    super();

    createContent();
  }

  public void setFooter(Component footer) {
    footer.getElement().setAttribute("slot", "footer");
    UI.getCurrent()
        .access(
            () -> {
              if (this.header != null) {
                this.remove(this.header);
              }
              add(this.header = footer);
            });
  }

  public void setHeader(Component header) {
    header.getElement().setAttribute("slot", "header");
    UI.getCurrent()
        .access(
            () -> {
              if (this.header != null) {
                this.remove(this.header);
              }
              add(this.header = header);
            });
  }

  public void setContent(Component content) {
    UI.getCurrent()
        .access(
            () -> {
              this.content.clear();
              this.content.add(content);
            });
  }

  protected void createContent() {
    content = new AirePanel();
    content.getElement().setAttribute("slot", "content");
    add(content);
  }
}
