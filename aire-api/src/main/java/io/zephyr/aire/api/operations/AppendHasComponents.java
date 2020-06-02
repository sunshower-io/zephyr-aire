package io.zephyr.aire.api.operations;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import io.zephyr.aire.api.Instantiator;
import io.zephyr.aire.api.Operation;

public class AppendHasComponents<T extends Component> implements Operation<HasComponents> {

  private final Class<T> type;

  public AppendHasComponents(Class<T> type) {
    this.type = type;
  }

  @Override
  public void apply(HasComponents field, Instantiator instantiator) {
    field.add(instantiator.instantiate(type));
  }
}
