package io.zephyr.aire;

import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.button.Button;
import io.zephyr.aire.api.*;
import io.zephyr.aire.test.*;
import io.zephyr.aire.test.core.AireTestConfiguration;
import org.springframework.test.context.ContextConfiguration;

import static io.zephyr.aire.api.Views.append;
import static org.junit.jupiter.api.Assertions.assertEquals;

@AireTest
@ContextConfiguration(classes = {AireConfiguration.class, AireTestConfiguration.class})
class VaadinViewManagerTest {

  private ComponentDefinition<HasComponents> append = append(Button.class).to(":main:header");

  @ViewTest(MainView.class)
  @EditView(withField = "append")
  void ensureViewManagerHasViewRegistered(@Context ViewContext context) {
    assertEquals(context.getComponentDefinitions().size(), 1);
  }

  @ViewTest(MainView.class)
  @EditView(withField = "append")
  void ensureAppendingButtonInstanceResultsInButtonAppendedToHeader(@Element Button button) {
    assertEquals(button.getText().trim(), "");
  }
}
