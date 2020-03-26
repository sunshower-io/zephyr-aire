package io.zephyr.aire.extensions;

import io.zephyr.aire.AireConfiguration;
import io.zephyr.aire.AireTest;
import io.zephyr.aire.MainView;
import io.zephyr.aire.ScanRoutes;
import io.zephyr.aire.api.ExtensionPointRegistry;
import io.zephyr.aire.elements.AireHeader;
import io.zephyr.aire.test.AireTestConfiguration;
import io.zephyr.aire.test.AireTestContext;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;

@AireTest
@ScanRoutes("io.zephyr.aire")
@ContextConfiguration(classes = {AireConfiguration.class, AireTestConfiguration.class})
class AireExtensionPointRegistryTest {

  @Inject AireTestContext context;
  @Inject private ExtensionPointRegistry registry;

  @Test
  void ensureScanningMainViewProducesCorrectNumberOfExtensionPointDefinitions() {

    context.resolveFirst(MainView.class);
    val exts = registry.getExtensionPoints();
    assertEquals(exts.size(), 6);
  }

  @Test
  void ensureHeaderSlotIsFilled() {
    context.resolveFirst(MainView.class);
    val def = registry.getDefinition(":ui:main:header");
    assertEquals(def.getType(), AireHeader.class);
  }

  @Test
  void ensureMainHasCorrectChildren() {
    context.resolveFirst(MainView.class);
    val children = registry.getChildren(":ui:main");
    assertEquals(4, children.size());
  }
}
