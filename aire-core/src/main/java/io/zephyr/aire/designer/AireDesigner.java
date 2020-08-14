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

  static final PropertyDescriptor<Boolean, Boolean> CONNECTABLE =
      PropertyDescriptors.propertyWithDefault("connectable", false);

  static final PropertyDescriptor<Boolean, Boolean> GRID_ENABLED =
      PropertyDescriptors.propertyWithDefault("gridEnabled", false);

  static final AtomicInteger generator = new AtomicInteger();

  final String id;

  public AireDesigner(String id) {
    this.id = id;
    ID.set(this, id);
  }

  public AireDesigner() {
    this("designer-" + generator.incrementAndGet());
  }

  public void setConnectable(boolean connectable) {
    CONNECTABLE.set(this, connectable);
  }

  public boolean isConnectable() {
    return CONNECTABLE.get(this);
  }

  public String getDesignerId() {
    return id;
  }

  public void addGrid() {
    GRID_ENABLED.set(this, !GRID_ENABLED.get(this));
  }
}
