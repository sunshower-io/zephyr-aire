package io.zephyr.aire;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.server.RouteRegistry;
import com.vaadin.flow.server.VaadinContext;
import com.vaadin.flow.server.startup.ApplicationRouteRegistry;
import io.zephyr.aire.api.ViewManager;

import javax.inject.Inject;

public class VaadinViewManager implements ViewManager {

  @Inject private VaadinContext context;

  @Override
  @SuppressWarnings("unchecked")
  public void register(Class<?> route) {
    ApplicationRouteRegistry ctx = ApplicationRouteRegistry.getInstance(context);
    RouteConfiguration.forRegistry(ctx).setAnnotatedRoute((Class<? extends Component>) route);
  }
}
