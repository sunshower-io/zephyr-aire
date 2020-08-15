package io.zephyr.aire.components.designer;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.shared.Registration;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Tag("aire-designer")
@JsModule("application/aire/designer/elements/designer.ts")
@CssImport("./styles/aire/components/designer/aire-designer.css")
public class AireDesigner extends Div {

  /** the context-unique identifier for this component */
  static final PropertyDescriptor<String, String> ID =
      PropertyDescriptors.propertyWithDefault("id", "_unregistered");

  /** determine whether this designer permits connections between vertices */
  static final PropertyDescriptor<Boolean, Boolean> CONNECTABLE =
      PropertyDescriptors.propertyWithDefault("connectable", false);

  /** determine whether this designer shows a grid */
  static final PropertyDescriptor<Boolean, Boolean> GRID_ENABLED =
      PropertyDescriptors.propertyWithDefault("gridEnabled", false);

  static final PropertyDescriptor<Boolean, Boolean> GUIDES_ENABLED =
      PropertyDescriptors.propertyWithDefault("guidesEnabled", false);

  static final PropertyDescriptor<Boolean, Boolean> GRID_SNAP_ENABLED =
      PropertyDescriptors.propertyWithDefault("gridSnapEnabled", false);

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
  /**
   * @param connectable sets the connectable status of this designer
   * @see {@link #CONNECTABLE}
   */
  public void setConnectable(boolean connectable) {
    CONNECTABLE.set(this, connectable);
  }

  public boolean isConnectable() {
    return CONNECTABLE.get(this);
  }

  public void setGridEnabled(boolean enabled) {
    GRID_ENABLED.set(this, enabled);
  }

  /** @return true if this designer instance has grids enabled, false otherwise */
  public boolean isGridEnabled() {
    return GRID_ENABLED.get(this);
  }

  public void setGuidesEnabled(boolean enabled) {
    GUIDES_ENABLED.set(this, enabled);
  }

  public boolean isGuidesEnabled() {
    return GUIDES_ENABLED.get(this);
  }

  public boolean isGridSnapEnabled() {
    return GRID_SNAP_ENABLED.get(this);
  }

  public void setGridSnapEnabled(boolean snapEnabled) {
    GRID_SNAP_ENABLED.set(this, snapEnabled);
  }

  /** listeners */
  public Registration addModelChangedListener(ComponentEventListener<ModelChangedEvent> listener) {
    return addListener(ModelChangedEvent.class, listener);
  }
}
