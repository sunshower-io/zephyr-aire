package io.zephyr.aire.test.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AireTestConfiguration {

  @Bean
  public AireTestContext aireTestContext(ApplicationContext context) {
    return new AireTestContext(context);
  }
}
