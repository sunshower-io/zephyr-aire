package io.zephyr.aire.components.controls;

import com.vaadin.flow.server.VaadinSession;
import io.zephyr.aire.AireConfiguration;
import io.zephyr.aire.common.ModuleResource;
import io.zephyr.aire.test.AireTest;
import io.zephyr.aire.test.core.AireTestConfiguration;
import io.zephyr.kernel.Module;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;

import javax.inject.Inject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

@AireTest
@ContextConfiguration(classes = {AireConfiguration.class, AireTestConfiguration.class})
class ModuleResourceTest {

  private ModuleResource resource;

  @Inject private Module module;
  @Mock private VaadinSession session;

  @BeforeEach
  void setUp() {
    resource = new ModuleResource("icon.svg", module);
  }

  @Test
  void ensureResourceWorks() throws IOException {
    val bos = new ByteArrayOutputStream();
    resource.getWriter().accept(bos, session);
    assertTrue("must have content", bos.toString().contains("svg"));
  }
}
