package io.zephyr.admin;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.router.RouterLink;
import io.zephyr.aire.api.Session;
import io.zephyr.aire.api.ViewDecorator;
import io.zephyr.aire.api.ViewManager;
import io.zephyr.aire.layout.AireApplicationViewport;
import io.zephyr.api.ModuleContext;
import io.zephyr.api.ServiceReference;
import io.zephyr.api.ServiceTracker;
import io.zephyr.kernel.events.Event;
import io.zephyr.kernel.events.EventListener;
import io.zephyr.kernel.events.EventType;

import static io.zephyr.api.ServiceEvents.REGISTERED;

public class ModuleActivator
    implements io.zephyr.api.ModuleActivator,
        EventListener<ServiceReference<ViewManager>>,
        ViewDecorator<AireApplicationViewport> {

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
    vm.register("aire.views.primary", this);
    vm.register(TestRoute.class);
    vm.register(TestRoute2.class);
  }

  @Override
  public void decorate(AireApplicationViewport value, Session session) {

    RouterLink link = new RouterLink("sup", TestRoute.class);
    RouterLink link2 = new RouterLink("sup2", TestRoute2.class);
    value.add(AireApplicationViewport.Region.PrimaryNavigation, link);
    value.add(AireApplicationViewport.Region.PrimaryNavigation, link2);
  }
}
