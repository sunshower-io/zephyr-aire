package io.aire.core;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;

public interface AireContainer extends AireComponent, HasComponents {

  void add(Component child);

}
