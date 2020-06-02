package io.zephyr.aire.api.builders;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import io.zephyr.aire.api.ComponentDefinition;
import io.zephyr.aire.api.PropertyBasedComponentDefinition;
import io.zephyr.aire.api.operations.AppendHasComponents;

import java.util.Set;

public class AppendCompositeDefinitionBuilder<T extends Component> {

  private final Class<T> type;

  public AppendCompositeDefinitionBuilder(final Class<T> type) {
    this.type = type;
  }

  public ComponentDefinition<HasComponents> to(String... paths) {
    return new PropertyBasedComponentDefinition<>(new AppendHasComponents<T>(type), Set.of(paths));
  }
}
