package io.zephyr.aire;

import io.zephyr.aire.api.ViewContext;
import io.zephyr.aire.api.ViewManager;

public class VaadinViewManager implements ViewManager {
  @Override
  public ViewContext newContext() {
    return null;
  }

  //  private final VaadinContext context;
  //  private final ComponentRegistry componentRegistry;
  //  private final ExtensionPointRegistry extensionPointRegistry;
  //
  //  public VaadinViewManager(
  //      final VaadinContext context,
  //      final ComponentRegistry componentRegistry,
  //      final ExtensionPointRegistry extensionPointRegistry) {
  //    this.context = context;
  //    this.componentRegistry = componentRegistry;
  //    this.extensionPointRegistry = extensionPointRegistry;
  //  }
  //
  //  @Override
  //  @SuppressWarnings("unchecked")
  //  public boolean hasRoute(Class<?> route) {
  //
  //    return true;
  //  }
  //
  //  @Override
  //  @SuppressWarnings("unchecked")
  //  public boolean registerRoute(Class<?> route) {
  //    try {
  //      val routeRegistry = ApplicationRouteRegistry.getInstance(context);
  //      RouteConfiguration.forRegistry(routeRegistry)
  //          .setAnnotatedRoute((Class<? extends Component>) route);
  //    } catch (AmbiguousRouteConfigurationException ex) {
  //      return false;
  //    }
  //    return true;
  //  }
  //
  //  @Override
  //  @SuppressWarnings("unchecked")
  //  public boolean unregisterRoute(Class<?> route) {
  //    val routeRegistry =
  //        ApplicationRouteRegistry.getInstance(context);
  //    RouteConfiguration.forRegistry(routeRegistry).removeRoute((Class<? extends Component>)
  // route);
  //    return true;
  //  }
  //
  //  @Override
  //  public ComponentRegistry getComponentRegistry() {
  //    return componentRegistry;
  //  }
  //
  //  @Override
  //  public ExtensionPointRegistry getExtensionPointRegistry() {
  //    return extensionPointRegistry;
  //  }
}
