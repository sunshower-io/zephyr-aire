package io.zephyr.aire.api;

import com.vaadin.flow.router.RouteData;

import java.util.List;

public interface ViewManager {

  void register(Class<?> type);

  void register(String path, Class<?> type);


  <T> ViewDecoratorRegistration register(Class<T> viewType, ViewDecorator<T> decorator);

  <T> ViewDecoratorRegistration register(String key, ViewDecorator<T> decorator);

  List<RouteData> getRoutes();

  <T> void unregister(Class<T> type);
}
