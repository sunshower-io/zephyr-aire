package io.zephyr.aire;

import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Header;
import io.zephyr.aire.api.*;
import io.zephyr.aire.elements.AireHeader;
import io.zephyr.aire.test.*;
import lombok.val;

import java.util.List;

import static io.zephyr.aire.api.Views.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@AireTest
class VaadinViewManagerTest {

  @ViewTest(MainView.class)
  void ensureViewManagerHasViewRegistered(@Context ViewContext context) {
    assertEquals(context.getComponentDefinitions().size(), 1);
  }

  @ViewTest(MainView.class)
  @EditView(withMethods = {"appendInstantiated", "instance"})
  void ensureValueIsSelectable(@Element("//*[@class='aire-header']") AireHeader header) {
    assertTrue(header.getElement().getClassList().contains("aire-header"));
  }

  @ViewTest(MainView.class)
  void ensureHeaderHasCorrectClass(@Element Header header) {
    assertTrue(header.getClassNames().contains("aire-header"));
  }

  @ViewTest(MainView.class)
  void ensureButtonIsAppendedWithInstance(@Elements List<Button> buttons) {
    val button = buttons.stream().filter(t -> t.getText().equals("Hello!")).findAny();
    assertTrue(button.isPresent());
  }

  private ComponentDefinition<HasComponents> appendInstantiated() {
    return append(Button.class).to(":main:header");
  }

  @EditView
  private ComponentDefinition<HasComponents> instance() {
    return appendWith(() -> new Button("Hello!")).to(":main:header");
  }
}
