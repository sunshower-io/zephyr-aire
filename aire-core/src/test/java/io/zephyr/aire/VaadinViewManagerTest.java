package io.zephyr.aire;

import io.zephyr.kernel.core.Kernel;
import io.zephyr.kernel.core.KernelLifecycle;
import io.zephyr.kernel.launch.KernelLauncher;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;

@AireTest
@ContextConfiguration(classes = AireConfiguration.class)
class VaadinViewManagerTest {

  @Inject private Kernel kernel;

  @Test
  void ensureKernelIsInjectable() {
    assertNotNull(kernel, "kernel must be injectable");
  }

  @Test
  void ensureKernelIsRunning() {
    assertEquals(
        kernel.getLifecycle().getState(), KernelLifecycle.State.Running, "state must be RUNNING");
  }
}
