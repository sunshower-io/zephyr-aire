package com.motorefi.maestro;

import com.motorefi.maestro.configurations.PersistenceConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = PersistenceConfiguration.class)
public class SchemaTest {

  @Inject private PersistenceConfiguration configuration;

  @Test
  void whatever() {
    System.out.println(configuration);
  }
}
