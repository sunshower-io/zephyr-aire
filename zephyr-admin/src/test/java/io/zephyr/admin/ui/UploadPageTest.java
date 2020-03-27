package io.zephyr.admin.ui;

import com.vaadin.flow.component.UI;
import io.zephyr.admin.ZephyrAdminConfiguration;
import io.zephyr.aire.AireConfiguration;
import io.zephyr.aire.AireTest;
import io.zephyr.aire.ScanRoutes;
import io.zephyr.aire.api.ViewManager;
import io.zephyr.aire.test.AireTestConfiguration;
import io.zephyr.aire.test.AireTestContext;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@AireTest
@ScanRoutes
@ContextConfiguration(
    classes = {
      AireConfiguration.class,
      AireTestConfiguration.class,
      ZephyrAdminConfiguration.class
    })
class UploadPageTest {

  @Inject private AireTestContext context;
  @Inject private ViewManager viewManager;

  @Test
  void ensureNavigatingToRouteWorks() {
    viewManager.registerRoute(UploadPage.class);
    UI.getCurrent().navigate("plugins");
    val page = context.resolveFirst(UploadPage.class);
    assertNotNull(page);
  }
}
