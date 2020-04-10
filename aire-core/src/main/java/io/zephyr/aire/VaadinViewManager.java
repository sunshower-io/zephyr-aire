package io.zephyr.aire;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.router.Router;
import com.vaadin.flow.server.AmbiguousRouteConfigurationException;
import com.vaadin.flow.server.VaadinContext;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.startup.ApplicationRouteRegistry;
import io.zephyr.aire.api.ComponentRegistry;
import io.zephyr.aire.api.ExtensionPointRegistry;
import io.zephyr.aire.api.ViewManager;
import lombok.val;

public class VaadinViewManager implements ViewManager {

  private final VaadinContext context;
  private final ComponentRegistry componentRegistry;
  private final ExtensionPointRegistry extensionPointRegistry;

  public VaadinViewManager(
      final VaadinContext context,
      final ComponentRegistry componentRegistry,
      final ExtensionPointRegistry extensionPointRegistry) {
    this.context = context;
    this.componentRegistry = componentRegistry;
    this.extensionPointRegistry = extensionPointRegistry;
  }

  @Override
  @SuppressWarnings("unchecked")
  public boolean hasRoute(Class<?> route) {

    return true;
  }

  @Override
  @SuppressWarnings("unchecked")
  public boolean registerRoute(Class<?> route) {
    try {
      val routeRegistry = ApplicationRouteRegistry.getInstance(context);
      RouteConfiguration.forRegistry(routeRegistry)
          .setAnnotatedRoute((Class<? extends Component>) route);
    } catch (AmbiguousRouteConfigurationException ex) {
      return false;
    }
    return true;
  }

  @Override
  @SuppressWarnings("unchecked")
  public boolean unregisterRoute(Class<?> route) {
    val routeRegistry =
        ApplicationRouteRegistry.getInstance(context);
    RouteConfiguration.forRegistry(routeRegistry).removeRoute((Class<? extends Component>) route);
    return true;
  }

  @Override
  public ComponentRegistry getComponentRegistry() {
    return componentRegistry;
  }

  @Override
  public ExtensionPointRegistry getExtensionPointRegistry() {
    return extensionPointRegistry;
  }
}
