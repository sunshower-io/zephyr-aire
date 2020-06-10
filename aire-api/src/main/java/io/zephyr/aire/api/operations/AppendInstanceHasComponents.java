package io.zephyr.aire.api.operations;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import io.zephyr.aire.api.Instantiator;
import io.zephyr.aire.api.Operation;

import java.util.function.Supplier;

public class AppendInstanceHasComponents<T extends Component> implements Operation<HasComponents> {

  final Supplier<T> instance;

  public AppendInstanceHasComponents(Supplier<T> instance) {
    this.instance = instance;
  }

  @Override
  public void apply(HasComponents field, Instantiator instantiator) {
    field.add(instance.get());
  }
}
