package io.zephyr.aire.test;

import com.github.mvysny.kaributesting.v10.MockVaadin;
import com.github.mvysny.kaributesting.v10.Routes;
import com.vaadin.flow.server.VaadinService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;

@Slf4j
public class RouteScanningTestExecutionListener implements TestExecutionListener {

  private Routes routes;

  public void beforeTestClass(TestContext testContext) throws Exception {
    val type = testContext.getTestClass();
    if (type.isAnnotationPresent(ScanRoutes.class)) {
      val toScan = type.getAnnotation(ScanRoutes.class);
      val routes = toScan.value();
      if (routes.equals("__DEFAULT__")) {
        log.info("Scanning package: " + type.getPackageName());
        this.routes = new Routes().autoDiscoverViews(type.getPackageName());
      } else {
        log.info("Scanning package: " + type.getPackageName());
        this.routes = new Routes().autoDiscoverViews(routes, true);
      }
    } else {
      this.routes = new Routes().autoDiscoverViews();
    }
  }

  public void beforeTestMethod(TestContext testContext) throws Exception {

    MockVaadin.setup(
        routes,
        (t, u) -> {
          val service = new TestVaadinService(t, u, testContext.getApplicationContext());
          VaadinService.setCurrent(service);
          return service;
        });
  }

  public void afterTestClass(TestContext context) {
    MockVaadin.tearDown();
    VaadinService.setCurrent(null);
  }
}
