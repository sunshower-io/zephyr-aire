package io.zephyr.aire.api;

import com.vaadin.flow.component.Component;
import io.zephyr.aire.api.operations.AppendHasComponents;
import lombok.val;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ViewsTest {

  @Test
  void ensureComponentDefinitionIsCorrectForAppend() {
    val result = Views.append(Component.class).to(":header");
    assertTrue(result.getOperation() instanceof AppendHasComponents);
  }
}
