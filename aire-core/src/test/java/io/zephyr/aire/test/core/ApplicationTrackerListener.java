package io.zephyr.aire.test.core;

import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;

import java.util.concurrent.atomic.AtomicReference;

public class ApplicationTrackerListener implements TestExecutionListener {

  private static final AtomicReference<ApplicationContext> context;

  static {
    context = new AtomicReference<>();
  }

  @Override
  public void beforeTestClass(TestContext testContext) throws Exception {
    context.set(testContext.getApplicationContext());
  }

  @Override
  public void afterTestClass(TestContext testContext) throws Exception {
    context.set(null);
  }

  public static ApplicationContext getContext() {
    return context.get();
  }
}
