package io.zephyr.aire.test;

import io.zephyr.kernel.Module;
import io.zephyr.kernel.concurrency.ModuleThread;
import io.zephyr.kernel.core.Kernel;
import io.zephyr.spring.embedded.EmbeddedModule;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AireTestConfiguration {


  @Bean
  public AireTestContext aireTestContext() {
    return new AireTestContext();
  }
}
