package io.zephyr.aire.annotation;

import io.zephyr.aire.api.ExtensionPoint;
import io.zephyr.aire.api.ExtensionPointDefinition;
import io.zephyr.aire.extensions.AireExtensionPointRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.IntrospectionException;

import static org.junit.Assert.assertEquals;

class ExtensionPointScannerTest {

  private ExtensionPointScanner scanner;
  private AireExtensionPointRegistry registry;
  private ExtensionPointDefinition<?> definition;

  @BeforeEach
  void setUp() {
    registry = new AireExtensionPointRegistry();
    scanner = new ExtensionPointScanner(registry);
  }

  @Test
  void ensureScanningExtensionPointProducesCorrectResult() throws IntrospectionException {

    @ExtensionPoint(location = ":ui:whatever")
    class Ext {}
    definition = scanner.scan(Ext.class, Ext.class.getAnnotation(ExtensionPoint.class));

    assertEquals(registry.getExtensionPoints().size(), 1);
  }
}
