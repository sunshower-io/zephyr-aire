package io.zephyr.aire;

import io.zephyr.aire.api.ViewManager;
import io.zephyr.kernel.core.Kernel;
import io.zephyr.kernel.core.KernelLifecycle;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;

@AireTest
@ContextConfiguration(classes = AireConfiguration.class)
class VaadinViewManagerTest {

  @Inject private Kernel kernel;

  @Inject private ViewManager viewManager;

  @Test
  void ensureKernelIsInjectable() {
    assertNotNull(kernel, "kernel must be injectable");
  }

  @Test
  void ensureKernelIsRunning() {
    assertEquals(
        kernel.getLifecycle().getState(), KernelLifecycle.State.Running, "state must be RUNNING");
  }

  @Test
  void ensureViewmanagerIsInjected() {
    assertNotNull(viewManager, "view manager must be injected");
  }

  @Test
  void ensureViewManagerHasRoute() {
    viewManager.register(MainView.class);
    assertEquals(1, viewManager.getRoutes().size(), "must have one route");
    viewManager.unregister(MainView.class);
    assertTrue(viewManager.getRoutes().isEmpty(), "must remove route");
  }
}
