package io.zephyr.aire.api.builders;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import io.zephyr.aire.api.ComponentDefinition;
import io.zephyr.aire.api.PropertyBasedComponentDefinition;
import io.zephyr.aire.api.operations.AppendInstanceHasComponents;

import java.util.Set;
import java.util.function.Supplier;

public class AppendInstanceDefinitionBuilder<T extends Component> {
  private final Supplier<T> instance;

  public AppendInstanceDefinitionBuilder(Supplier<T> instance) {
    this.instance = instance;
  }

  public ComponentDefinition<HasComponents> to(String... paths) {
    return new PropertyBasedComponentDefinition<>(
        new AppendInstanceHasComponents<>(instance), Set.of(paths));
  }
}
