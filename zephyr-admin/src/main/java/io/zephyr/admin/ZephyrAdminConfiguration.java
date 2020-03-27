package io.zephyr.admin;

import io.zephyr.admin.ui.UploadPage;
import io.zephyr.aire.api.ViewManager;
import lombok.val;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

@Configuration
public class ZephyrAdminConfiguration implements ApplicationListener<ContextRefreshedEvent> {

  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    val ctx = contextRefreshedEvent.getApplicationContext();
    val viewManager = ctx.getBean(ViewManager.class);
    viewManager.registerRoute(UploadPage.class);
  }
}
