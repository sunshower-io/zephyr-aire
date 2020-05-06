package io.zephyr.aire.decorate;

import com.vaadin.flow.component.button.Button;
import io.zephyr.aire.api.Container;
import io.zephyr.aire.api.Slot;
import io.zephyr.aire.api.SlotRegistration;
import io.zephyr.aire.elements.AireHeader;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;


class SlotDecoratorTest {

  private SlotDecorator decorator;
  private SlotRegistration registration;

  @BeforeEach
  void setUp() {}

  @Test
  void ensureAppendingToSlotByFieldWorks() {

    @Container(":test")
    class TestContainer {

      @Slot(":header")
      private AireHeader header;
    }

    registration = new SlotRegistration(Button.class, Set.of(":test:header"));
    decorator = new SlotDecorator(registration);

    val instance = new TestContainer();
    val result = decorator.decorate(TestContainer.class, instance);
  }
}
