package io.zephyr.aire;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

@WebAppConfiguration
@SpringBootTest(classes = Application.class)
public class AireConfigurationTest {
  @Test
  void ensureApplicationStartsCorrectly() {}
}
