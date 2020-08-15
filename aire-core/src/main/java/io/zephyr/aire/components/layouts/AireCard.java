package io.zephyr.aire.components.layouts;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import io.aire.core.AireComponent;

@Tag("aire-card")
@JsModule("lit-element")
@JsModule("./components/aire/aire-card.ts")
@CssImport("./styles/aire/components/aire-card.css")
@NpmPackage(value = "lit-element", version = "^2.3.1")
public class AireCard extends Component implements HasComponents, AireComponent {

  private Component footer;
  private Component content;
  private Component header;

  public AireCard() {
    super();
  }

  public void setFooter(Component footer) {
    footer.getElement().setAttribute("slot", "footer");
    access(() -> doRemoveFooter(footer));
  }

  public void setHeader(Component header) {
    header.getElement().setAttribute("slot", "header");
    access(() -> doRemoveHeader(header));
  }

  private void doRemoveHeader(Component header) {
    if (this.header != null) {
      this.remove(this.header);
    }
    add(this.header = header);
  }

  public void setContent(Component content) {
    content.getElement().setAttribute("slot", "content");
    access(() -> doRemoveContent(content));
  }

  private void doRemoveContent(Component content) {
    if (this.content != null) {
      this.remove(this.content);
    }
    add(this.content = content);
  }

  private void doRemoveFooter(Component footer) {
    if (this.footer != null) {
      this.remove(this.footer);
    }
    add(this.footer = footer);
  }
}
