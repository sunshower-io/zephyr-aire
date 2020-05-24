package io.zephyr.aire.core.ui;

import io.zephyr.api.security.Session;
import io.zephyr.aire.api.*;
import io.zephyr.aire.reflect.PropertyDescriptor;
import io.zephyr.aire.reflect.PropertyTraversalCallback;
import io.zephyr.aire.reflect.Reflection;
import io.zephyr.kernel.Coordinate;
import io.zephyr.kernel.Module;
import lombok.val;

import java.lang.annotation.Annotation;
import java.util.*;
import java.util.stream.Collectors;

public class VaadinViewManager implements ViewManager {

  private final Map<Coordinate, ViewContext> registeredContexts;
  private final Map<String, List<InstantiableComponent>> componentDefinitions;

  public VaadinViewManager() {
    this.registeredContexts = new HashMap<>();
    this.componentDefinitions = new HashMap<>();
  }

  @Override
  public ViewContext newContext(Module module, Instantiator instantiator) {
    val ctx = new VaadinViewContext(this, module, instantiator);
    registeredContexts.put(module.getCoordinate(), ctx);
    return ctx;
  }

  @Override
  public Object decorate(Class<?> type, Object instance) {
    if (type.isAnnotationPresent(Container.class)) {
      for (Class<?> t = type; t != Object.class; t = t.getSuperclass()) {
        val container = t.getAnnotation(Container.class);
        if (container == null) {
          continue;
        }

        Reflection.traverseProperties(
            t,
            t.getSuperclass(),
            Slot.class,
            (Annotation a) -> ((Slot) a).value(),
            new TreeEditorCallback(t, container.value(), instance));
      }
    }

    return instance;
  }

  public void unregister(ViewContext viewContext) {
    val host = viewContext.getHost();
    registeredContexts.remove(host.getCoordinate());

    for (val definition : componentDefinitions.entrySet()) {
      val iter = definition.getValue().iterator();
      while (iter.hasNext()) {
        val next = iter.next();
        if (next.host.equals(host.getCoordinate())) {
          iter.remove();
        }
      }
    }
  }

  @Override
  public void close() {
    registeredContexts.clear();
  }

  public <T> void registerDefinition(
      String location,
      ComponentDefinition<T> componentDefinition,
      Instantiator instantiator,
      Coordinate host) {

    var definitionSet = componentDefinitions.get(location);
    if (definitionSet == null) {
      definitionSet = new ArrayList<>();
      definitionSet.add(new InstantiableComponent(instantiator, componentDefinition, host));
      componentDefinitions.put(location, definitionSet);
    } else {
      componentDefinitions
          .get(location)
          .add(new InstantiableComponent(instantiator, componentDefinition, host));
    }
  }

  public Set<ComponentDefinition<?>> getDefinitions(Coordinate coordinate) {
    return componentDefinitions.values().stream()
        .flatMap(Collection::stream)
        .filter(t -> coordinate.equals(t.host))
        .map(t -> t.definition)
        .collect(Collectors.toSet());
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

  static final class InstantiableComponent {
    final Coordinate host;
    final Instantiator instantiator;
    final ComponentDefinition<?> definition;

    InstantiableComponent(
        Instantiator instantiator, ComponentDefinition<?> definition, Coordinate host) {
      this.host = host;
      this.definition = definition;
      this.instantiator = instantiator;
    }

    public void apply(Object property, Session o) {
      definition.apply(property, o, instantiator);
    }
  }

  final class TreeEditorCallback implements PropertyTraversalCallback<Object> {
    final Class<?> type;
    final Stack<String> segments;
    private Object currentInstance;

    TreeEditorCallback(Class<?> typeRoot, String root, Object instance) {
      this.type = typeRoot;
      this.segments = new Stack<>();
      this.currentInstance = instance;
      segments.push(root);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public void onProperty(PropertyDescriptor descriptor) {
      try {
        segments.push(descriptor.getName());
        val definitions = componentDefinitions.get(String.join(":", segments));
        var previousInstance = currentInstance;

        if (definitions != null) {

          val value = descriptor.get(currentInstance);
          for (val definition : definitions) {
            definition.apply(value, null);
          }
          currentInstance = value;
        }

        Reflection.traverseProperties(
            descriptor.getType(),
            descriptor.getType().getSuperclass(),
            Slot.class,
            a -> ((Slot) a).value(),
            this);
        currentInstance = previousInstance;
      } catch (ReflectiveOperationException ex) {
        throw new ViewDecorationFailureException(ex);
      } finally {
        segments.pop();
      }
    }
  }
}
