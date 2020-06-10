package io.zephyr.aire.api;

import com.vaadin.flow.component.Component;
import io.zephyr.aire.api.builders.AppendCompositeDefinitionBuilder;
import io.zephyr.aire.api.builders.AppendInstanceDefinitionBuilder;

import java.util.function.Supplier;

public class Views {

  public static <T extends Component> AppendCompositeDefinitionBuilder<T> append(Class<T> type) {
    return new AppendCompositeDefinitionBuilder<>(type);
  }

  public static <T extends Component> AppendInstanceDefinitionBuilder<T> appendWith(
      Supplier<T> instance) {
    return new AppendInstanceDefinitionBuilder<>(instance);
  }
}
