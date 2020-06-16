package io.zephyr.aire;

import io.zephyr.aire.test.AireTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

import static org.junit.jupiter.api.Assertions.assertEquals;

@AireTest
public class AireConfigurationTest {

  @Value("${deployment.location}")
  String value;

  @Test
  void ensureDeploymentLocationIsCorrect() {
    assertEquals(value, "data/plugins");
  }
}
