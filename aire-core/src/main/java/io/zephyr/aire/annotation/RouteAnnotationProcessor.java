package io.zephyr.aire.annotation;

import com.vaadin.flow.router.Route;
import io.aire.ext.AnnotationProcessor;
import io.zephyr.aire.Coordinates;
import io.zephyr.aire.api.ViewManager;
import io.zephyr.kernel.Coordinate;
import io.zephyr.kernel.Module;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.lang.annotation.Annotation;

@Slf4j
public final class RouteAnnotationProcessor implements AnnotationProcessor {

  @Override
  public void process(ViewManager viewManager, Module module, Object definition, Class<?> type) {

    Coordinate coordinate = module.getCoordinate();
    val fragment = Coordinates.toFragment(coordinate);
    val annotationAttributes = type.getAnnotation(Route.class);
    val routeValue = String.format("%s/%s", fragment, annotationAttributes.value());
    if (!viewManager.containsRoute(routeValue)) {
      log.info("Binding {} to route {} from module {}", type, routeValue, coordinate);
      viewManager.register(routeValue, type);
    } else {
      log.debug("Route {} is already bound--not doing anything", routeValue);
    }
  }

  @Override
  public Class<? extends Annotation> getAnnotationType() {
    return Route.class;
  }

  @Override
  public boolean handles(Object metadata, Class<?> type) {
    if (type == null) {
      return false;
    }
    return type.isAnnotationPresent(Route.class);
  }
}
