package io.zephyr.aire.designer;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.internal.JsonSerializer;
import lombok.Getter;
import lombok.Setter;
import lombok.val;
import org.jsoup.select.Elements;

import java.io.Serializable;

@Tag("aire-designer")
@JsModule("application/aire/designer/elements/designer.ts")
@CssImport("./styles/aire/components/aire-designer.css")
public class AireDesigner extends Div {

  public AireDesigner() {
    addClassName("expand");
  }

  public void addElement() {
    val element = new Element();
    element.setName("Sup dood");
    getElement().callJsFunction("addElement", "coolbeans");
  }

  public static class Element implements Serializable {
    @Getter @Setter private String name;
  }
}
