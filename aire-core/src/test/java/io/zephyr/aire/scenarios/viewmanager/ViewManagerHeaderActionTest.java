package io.zephyr.aire.scenarios.viewmanager;

import io.zephyr.aire.AireConfiguration;
import io.zephyr.aire.api.ViewContext;
import io.zephyr.aire.api.ViewManager;
import io.zephyr.aire.test.AireTest;
import io.zephyr.aire.test.core.AireTestConfiguration;
import io.zephyr.aire.test.core.AireTestContext;
import io.zephyr.aire.test.ScanRoutes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.test.context.ContextConfiguration;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@AireTest
@ScanRoutes
@ContextConfiguration(classes = {AireConfiguration.class, AireTestConfiguration.class})
public class ViewManagerHeaderActionTest {

  @Inject private ViewManager viewManager;

  @Inject private AireTestContext testContext;
  private ViewContext context;

  @BeforeEach
  void setUp() {
//    context = viewManager.newContext(mock());
  }

  @AfterEach
  void tearDown() {
//    context.close();
  }

//  @Test
//  void ensureActionPlacedIntoHeaderResultsInActionBeingPlacedInDom() {
//    val slot = new SlotRegistration(MenuAction.class, Set.of(":ui:main:header"));
//    context = viewManager.newContext();
//    val registration = context.register(slot);
//    testContext.navigate(MainView.class);
//    val action = testContext.resolveFirst(MenuAction.class);
//    assertNotNull(action, "action must not be null");
//  }

  public static class MenuAction {}
}
