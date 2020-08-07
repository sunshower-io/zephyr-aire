package io.zephyr.aire.designer;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.internal.JsonSerializer;
import lombok.Getter;
import lombok.Setter;
import lombok.val;
import org.jsoup.select.Elements;

import java.io.Serializable;
import java.util.UUID;

@Tag("aire-designer")
@JsModule("application/aire/designer/elements/designer.ts")
@CssImport("./styles/aire/components/designer/aire-designer.css")
public class AireDesigner extends Div {

  static final PropertyDescriptor<String, String> idDescriptor =
      PropertyDescriptors.propertyWithDefault("id", "unregistered");

  final String id;

  public AireDesigner() {
    this.id = newId();
    addClassName("expand");
  }

  public void addElement() {
    val element = new Element();
    element.setName("Sup dood");
    getElement().callJsFunction("addElement", this.id);
  }

  public static class Element implements Serializable {
    @Getter @Setter private String name;
  }

  protected void onAttach(AttachEvent event) {
    idDescriptor.set(this, id);
  }

  protected void onDetach(DetachEvent event) {
    getElement().callJsFunction("unregister");
  }

  private String newId() {
    return UUID.randomUUID().toString();
  }
}
