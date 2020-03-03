package io.zephyr.aire;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.server.VaadinContext;
import com.vaadin.flow.server.startup.ApplicationRouteRegistry;
import io.aire.core.AireComponent;
import io.zephyr.aire.api.*;
import org.springframework.aop.support.AopUtils;

import javax.inject.Inject;
import java.util.*;

public class VaadinViewManager implements ViewManager, ViewDecoratorManager {

  @Inject private Session session;
  @Inject private VaadinContext context;

  private final Map<Class<?>, List<ViewDecorator<?>>> registrations;
  private final Map<String, List<ViewDecorator<?>>> namedRegistrations;

  public VaadinViewManager() {
    registrations = new HashMap<>();
    namedRegistrations = new HashMap<>();
  }

  @Override
  @SuppressWarnings("unchecked")
  public void register(Class<?> route) {
    ApplicationRouteRegistry ctx = ApplicationRouteRegistry.getInstance(context);
    RouteConfiguration.forRegistry(ctx).setAnnotatedRoute((Class<? extends Component>) route);
  }

  @Override
  public <T> ViewDecoratorRegistration register(Class<T> viewType, ViewDecorator<T> decorator) {
    ViewDecoratorRegistration registration =
        new DefaultViewDecoratorRegistration(this, viewType, decorator);
    addRegistration(viewType, decorator);
    return registration;
  }

  @Override
  public <T> ViewDecoratorRegistration register(String key, ViewDecorator<T> decorator) {
    ViewDecoratorRegistration registration =
        new DefaultViewDecoratorRegistration(this, key, decorator);
    addRegistration(key, decorator);
    return registration;
  }

  private <T> void addRegistration(String key, ViewDecorator<T> decorator) {
    namedRegistrations.computeIfAbsent(key, t -> new ArrayList<>()).add(decorator);
  }

  @Override
  public <T> void decorate(T view) {
    Class<T> type = getType(view);
    for (ViewDecorator<T> decorators : getDecorators(type)) {
      decorators.decorate(view, session);
    }
  }

  @Override
  public <T> void decorate(String key, T view) {
    for (ViewDecorator<T> decorators : this.<T>getDecorators(key)) {
      decorators.decorate(view, session);
    }
  }

  final <T> void removeRegistration(Class<T> viewType, ViewDecorator<T> decorator) {
    synchronized (registrations) {
      List<ViewDecorator<T>> types = getDecorators(viewType);
      doRemove(decorator, types);
    }
  }

  <T> void removeRegistration(String key, ViewDecorator<T> decorator) {
    synchronized (registrations) {
      List<ViewDecorator<T>> types = getDecorators(key);
      doRemove(decorator, types);
    }
  }

  private <T> void doRemove(ViewDecorator<T> decorator, List<ViewDecorator<T>> types) {
    Iterator<ViewDecorator<T>> iter = types.iterator();
    while (iter.hasNext()) {
      ViewDecorator<T> next = iter.next();
      if (next.equals(decorator)) {
        iter.remove();
      }
    }
  }

  @SuppressWarnings("unchecked")
  private <T> List<ViewDecorator<T>> getDecorators(String key) {
    Object result = namedRegistrations.get(key);
    if (result == null) {
      return Collections.emptyList();
    }
    return (List<ViewDecorator<T>>) result;
  }

  @SuppressWarnings("unchecked")
  private <T> List<ViewDecorator<T>> getDecorators(Class<T> type) {
    Object result = registrations.get(type);
    if (result == null) {
      return Collections.emptyList();
    }
    return (List<ViewDecorator<T>>) result;
  }

  @SuppressWarnings("unchecked")
  private <T> Class<T> getType(T view) {
    return (Class<T>) AopUtils.getTargetClass(view);
  }

  private <T> void addRegistration(Class<T> viewType, ViewDecorator<T> registration) {
    registrations.computeIfAbsent(viewType, t -> new ArrayList<>()).add(registration);
  }
}
