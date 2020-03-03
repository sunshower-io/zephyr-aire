package io.zephyr.aire.elements;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasComponents;
import io.aire.core.AireContainer;

public abstract class AbstractAireContainer<T extends Component & HasComponents>
    extends Composite<T> implements AireContainer {
  public void add(Component child) {
    getContent().add(child);
  }
}
