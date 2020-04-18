package io.zephyr.aire;

import com.vaadin.flow.server.*;
import com.vaadin.flow.shared.Registration;
import io.zephyr.aire.decorate.NavigationInjectionTracker;
import lombok.val;

public class AireVaadinServiceInitializerListener
    implements VaadinServiceInitListener, UIInitListener, ServiceDestroyListener {
  private Registration serviceInitRegistration;
  private Registration beforeLeaveRegistration;
  private Registration navigationRegistration;
  private Registration beforeEnterRegistration;

  @Override
  public void serviceDestroy(ServiceDestroyEvent serviceDestroyEvent) {
    serviceInitRegistration.remove();
    beforeEnterRegistration.remove();
    beforeLeaveRegistration.remove();
    navigationRegistration.remove();
  }

  @Override
  public void uiInit(UIInitEvent uiInitEvent) {
    val ui = uiInitEvent.getUI();
    val tracker = new NavigationInjectionTracker();
    beforeLeaveRegistration = ui.addBeforeLeaveListener(tracker);
    beforeEnterRegistration = ui.addBeforeEnterListener(tracker);
    navigationRegistration = ui.addAfterNavigationListener(tracker);
  }

  @Override
  public void serviceInit(ServiceInitEvent serviceInitEvent) {
    serviceInitRegistration = serviceInitEvent.getSource().addUIInitListener(this);
  }
}
