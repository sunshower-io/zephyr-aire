package io.zephyr.admin;

import io.zephyr.aire.api.ViewManager;
import io.zephyr.api.ModuleContext;
import io.zephyr.api.ServiceReference;
import io.zephyr.api.ServiceTracker;
import io.zephyr.kernel.events.Event;
import io.zephyr.kernel.events.EventListener;
import io.zephyr.kernel.events.EventType;

import static io.zephyr.api.ServiceEvents.REGISTERED;

public class ModuleActivator implements io.zephyr.api.ModuleActivator, EventListener<ServiceReference<ViewManager>> {

  ServiceTracker tracker;

  @Override
  public void start(ModuleContext moduleContext) throws Exception {
    System.out.println("Starting...");
    tracker = moduleContext.trackServices(t -> true);
    tracker.addEventListener(this, REGISTERED);
  }

  @Override
  public void stop(ModuleContext moduleContext) throws Exception {
    System.out.println("Stopping...");
    tracker.close();
  }

  @Override
  public void onEvent(EventType eventType, Event<ServiceReference<ViewManager>> event) {
    ViewManager vm = event.getTarget().getDefinition().get();
    vm.register(TestRoute.class);

  }
}
