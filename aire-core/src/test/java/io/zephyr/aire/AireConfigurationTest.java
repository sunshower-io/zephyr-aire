package io.zephyr.aire;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AireConfiguration.class)
public class AireConfigurationTest {
  @Test
  void ensureApplicationStartsCorrectly() {}
}
