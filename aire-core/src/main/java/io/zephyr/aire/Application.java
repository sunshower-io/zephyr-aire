package io.zephyr.aire;

import com.vaadin.flow.spring.SpringBootAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

@Import(AireConfiguration.class)
@SpringBootApplication(exclude = SpringBootAutoConfiguration.class)
public class Application extends SpringBootServletInitializer {

  private static ConfigurableApplicationContext context;

  public static void stop() {
    if (context != null) {
      context.close();
    }
  }

  public static void main(String[] args) {
    context = SpringApplication.run(Application.class, args);
  }
}
