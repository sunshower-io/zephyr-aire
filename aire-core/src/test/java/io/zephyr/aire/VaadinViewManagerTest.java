package io.zephyr.aire;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Header;
import io.zephyr.aire.api.*;
import io.zephyr.aire.core.ui.VaadinBridge;
import io.zephyr.aire.elements.AireHeader;
import io.zephyr.aire.reflect.PropertyBasedComponentDefinition;
import io.zephyr.aire.test.AireTest;
import io.zephyr.aire.test.AireTestConfiguration;
import io.zephyr.aire.test.AireTestContext;
import io.zephyr.aire.test.ScanRoutes;
import io.zephyr.kernel.Module;
import io.zephyr.kernel.core.Kernel;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import javax.inject.Inject;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@AireTest
@ScanRoutes(value = "io.zephyr.aire", mockStrategy = ScanRoutes.MockStrategy.None)
@ContextConfiguration(classes = {AireConfiguration.class, AireTestConfiguration.class})
class VaadinViewManagerTest {

  @Inject private Module module;

  @Inject AireTestContext context;

  @Inject private Kernel kernel;

  @Inject private ViewManager viewManager;
  private ViewContext viewContext;

  @Inject private ApplicationContext applicationContext;

  @Test
  void ensureViewManagerIsInjected() {
    assertNotNull(viewManager, "ViewManager must not be null");
  }

  @BeforeEach
  void setUp() {}

  @Test
  void ensureButtonIsAppendedToBody() {
    viewContext = viewManager.newContext(module, new VaadinBridge(applicationContext));
    val append = spy(new Append());
    val definition = new PropertyBasedComponentDefinition<>(append, Arrays.asList(":main:header"));
    viewContext.register(definition);

    context.withComponentRoute(
        MainView.class,
        () -> {
          context.navigate(MainView.class);
          val header = context.resolveFirst(AireHeader.class);

          val actualHeader = (Header) header.getChildren().findFirst().get();
          val child = (Button) actualHeader.getChildren().findFirst().get();
          assertEquals(child.getText(), "hello");
          viewContext.close();
        });
    verify(append, times(1)).apply(any(), any());
  }

  static class Append implements Operation<AireHeader> {

    @Override
    public void apply(AireHeader field, Instantiator instantiator) {
      field.add(new Button("hello"));
    }
  }
}
