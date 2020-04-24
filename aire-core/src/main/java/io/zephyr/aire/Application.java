package io.zephyr.aire;

import com.vaadin.flow.spring.SpringBootAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

@Import(AireConfiguration.class)
@SpringBootApplication(exclude = SpringBootAutoConfiguration.class)
public class Application extends SpringBootServletInitializer {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
