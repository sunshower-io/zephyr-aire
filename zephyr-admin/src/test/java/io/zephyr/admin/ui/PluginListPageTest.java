package io.zephyr.admin.ui;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H1;
import io.zephyr.aire.AireConfiguration;
import io.zephyr.aire.test.AireTest;
import io.zephyr.aire.test.ScanRoutes;
import io.zephyr.aire.api.ViewManager;
import io.zephyr.aire.test.AireTestConfiguration;
import io.zephyr.aire.test.AireTestContext;
import lombok.val;
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
    UI.getCurrent().navigate("plugins");
    val page = context.resolveFirst(PluginListPage.class);
    assertNotNull(page);

    val h1 = context.resolveFirst(H1.class);
    assertEquals(h1.getText().trim(), "zephyr-aire");
  }
}
