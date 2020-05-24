package io.zephyr.admin.ui;

import io.zephyr.aire.AireConfiguration;
import io.zephyr.aire.test.AireTest;
import io.zephyr.aire.test.ScanRoutes;
import io.zephyr.aire.api.ViewManager;
import io.zephyr.aire.test.core.AireTestConfiguration;
import io.zephyr.aire.test.core.AireTestContext;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@AireTest
@ScanRoutes
@ContextConfiguration(
    classes = {
      AireConfiguration.class,
      AireTestConfiguration.class,
    })
@Disabled
class PluginListPageTest {

  @Inject private AireTestContext context;
  @Inject private ViewManager viewManager;

  @Test
  void ensureNavigatingToRouteWorks() {
//    UI.getCurrent().navigate("plugins");
//    PluginListPage page = context.resolveFirst(PluginListPage.class);
//    assertNotNull(page);
//
//    val h1 = context.resolveFirst(H1.class);
//    assertEquals(h1.getText().trim(), "zephyr-aire");
  }
}
