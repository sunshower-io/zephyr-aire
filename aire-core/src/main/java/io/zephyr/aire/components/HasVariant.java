package io.zephyr.aire.components;

import com.vaadin.flow.component.HasElement;
import lombok.val;

public interface HasVariant extends HasElement {

  AireVariant current();

  default void setVariant(AireVariant variant) {
    val current = current();
    if (current != null) {
      current.unset(this);
    }
    variant.set(this);
    setCurrent(variant);
  }

  void setCurrent(AireVariant variant);
}
