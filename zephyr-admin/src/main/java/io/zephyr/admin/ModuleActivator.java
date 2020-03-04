package io.zephyr.admin;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;

import static io.zephyr.api.ServiceEvents.REGISTERED;

@SpringBootApplication
@Import(ZephyrAdminConfiguration.class)
public class ModuleActivator
    implements io.zephyr.api.ModuleActivator, ViewDecorator<AireApplicationViewport> {

  static final Logger log = LoggerFactory.getLogger(ModuleActivator.class);
  private AnnotationConfigApplicationContext context;

  @Override
  public void start(ModuleContext moduleContext) {
    log.info("zephyr-admin starting...");

    ApplicationContext parentContext = moduleContext.get(ApplicationContext.class);

    ViewManager viewManager = parentContext.getBean(ViewManager.class);
    context = new AnnotationConfigApplicationContext(ZephyrAdminConfiguration.class);
//    context.addBeanFactoryPostProcessor(new AireAnnotationProcessor(viewManager));
    context.setParent(parentContext);
    context.start();
    log.info("zephyr-admin started successfully");
  }

  @Override
  public void stop(ModuleContext moduleContext) throws Exception {
    log.info("zephyr-admin stopping...");
    context.close();
    log.info("zephyr-admin stopped successfully");
  }

  @Override
  public void decorate(AireApplicationViewport value, Session session) {}

  //  @Override
  //  public void onEvent(EventType eventType, Event<ServiceReference<ViewManager>> event) {
  //    ViewManager vm = event.getTarget().getDefinition().get();
  //    vm.register("aire.views.primary", this);
  //    vm.register(TestRoute.class);
  //    vm.register(TestRoute2.class);
  //  }
  //
  //  @Override
  //  public void decorate(AireApplicationViewport value, Session session) {
  //
  //    RouterLink link = new RouterLink("", TestRoute.class);
  //    link.add(new Span("hello"));
  //    Icon icon = new Icon(VaadinIcon.ABACUS);
  //    link.add(icon);
  //    RouterLink link2 = new RouterLink("sup2", TestRoute2.class);
  //    value.add(AireApplicationViewport.Region.PrimaryNavigation, link);
  //    value.add(AireApplicationViewport.Region.PrimaryNavigation, link2);
  //  }
}
