package io.zephyr.aire.designer;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Tag("aire-designer")
@JsModule("application/aire/designer/elements/designer.ts")
@CssImport("./styles/aire/components/designer/aire-designer.css")
public class AireDesigner extends Div {

  static final PropertyDescriptor<String, String> ID =
      PropertyDescriptors.propertyWithDefault("id", "_unregistered");

  static final AtomicInteger generator = new AtomicInteger();

  final String id;

  public AireDesigner(String id) {
    this.id = id;
    ID.set(this, id);
  }

  public AireDesigner() {
    this("designer-" + generator.incrementAndGet());
  }

  public String getDesignerId() {
    return id;
  }
}
