package io.zephyr.aire.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AireTestConfiguration {


  @Bean
  public AireTestContext aireTestContext() {
    return new AireTestContext();
  }
}
