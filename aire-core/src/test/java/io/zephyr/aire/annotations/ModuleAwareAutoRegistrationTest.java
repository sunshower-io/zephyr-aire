package io.zephyr.aire.annotations;

import com.vaadin.flow.router.Route;
import io.zephyr.aire.AireConfiguration;
import io.zephyr.aire.AireTest;
import io.zephyr.aire.annotation.ServiceLoaderAwareAnnotationPostProcessor;
import io.zephyr.aire.api.ViewManager;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@AireTest
@ContextConfiguration(
    classes = {AireConfiguration.class, ModuleAwareAutoRegistrationTest.Cfg.class})
public class ModuleAwareAutoRegistrationTest {
  @Inject private ViewManager viewManager;

  @Test
  void ensureThingsWork() {
    assertEquals(1, viewManager.getRoutes().size(), "must have one route");
  }

  @Configuration
  public static class Cfg {

    @Bean
    public TestRoute testRoute() {
      return new TestRoute();
    }

    @Bean
    public static ServiceLoaderAwareAnnotationPostProcessor routePostProcessor() {
      return new ServiceLoaderAwareAnnotationPostProcessor();
    }
  }

  @Route("test")
  public static class TestRoute {}
}
