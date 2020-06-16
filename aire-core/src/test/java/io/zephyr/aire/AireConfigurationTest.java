package io.zephyr.aire;

import io.zephyr.aire.test.AireTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@AireTest
@EnableConfigurationProperties
public class AireConfigurationTest {

  @Value("${deployment.locations}")
  String value;

  @Test
  void ensurePortIsCorrect() {
    System.out.println("VAL" + value);
  }
}
