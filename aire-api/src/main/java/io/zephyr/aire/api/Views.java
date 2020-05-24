package io.zephyr.aire.api;

import com.vaadin.flow.component.Component;
import io.zephyr.aire.api.builders.AppendCompositeDefinitionBuilder;

public class Views {

  public static <T extends Component> AppendCompositeDefinitionBuilder<T> append(Class<T> type) {
    return new AppendCompositeDefinitionBuilder<>(type);
  }
}
