package io.zephyr.aire;

import com.vaadin.flow.server.VaadinService;
import io.zephyr.aire.api.ViewManager;
import io.zephyr.aire.test.AireTest;
import io.zephyr.aire.test.AireTestConfiguration;
import io.zephyr.aire.test.AireTestContext;
import io.zephyr.aire.test.ScanRoutes;
import io.zephyr.kernel.core.Kernel;
import io.zephyr.kernel.core.KernelLifecycle;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;

@AireTest
@ScanRoutes
@ContextConfiguration(classes = {AireConfiguration.class, AireTestConfiguration.class})
class VaadinViewManagerTest {

  @Inject AireTestContext context;

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
  void ensureExtensionPointRegistryIsDefined() {
    assertNotNull(
        viewManager.getExtensionPointRegistry(), "extension point registry must not be null");
  }

  @Test
  void ensureFirstViewIsMain() {
    val page = context.resolveFirst(MainView.class);
    assertNotNull(page, "must exist");
  }

  @Test
  void ensureExtensionPointIsRegistered() {
    context.resolveFirst(MainView.class);
    assertEquals(
        6,
        viewManager.getExtensionPointRegistry().getExtensionPoints().size(),
        "must have one extension point registered");
  }

  @Test
  void ensureMainViewHasAHeader() {
    val page = context.resolveFirst(MainView.class);
    assertNotNull(page.getHeader(), "header must not be null");
  }

  @Test
  void ensureVaadinContextIsSet() {
    assertNotNull(VaadinService.getCurrent());
  }

  //  @Test
  //  void ensureMainViewHeaderCanHaveIconPlacedInIt() {
  //    val icon = context.resolveFirst(Icon.class);
  //    assertNull(icon, "must not have an icon");
  //  }
}
