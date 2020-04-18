package io.zephyr.aire.decorate;

import com.vaadin.flow.router.*;
import io.sunshower.gyre.Pair;
import io.zephyr.aire.api.Decorate;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.aop.support.AopUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.String.format;

@Slf4j
public class NavigationInjectionTracker
    implements BeforeLeaveListener, BeforeEnterListener, AfterNavigationListener {

  public NavigationInjectionTracker() {}

  /**
   * noop for now
   *
   * @param beforeLeaveEvent
   */
  @Override
  public void beforeLeave(BeforeLeaveEvent beforeLeaveEvent) {}

  @Override
  public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {}

  @Override
  public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {

    val chain = afterNavigationEvent.getActiveChain();
    val bindings = new HashMap<Class<?>, Object>(chain.size());
    val decoratedChain = new ArrayList<Pair<Class<?>, Object>>(chain.size());

    for (val element : chain) {
      val targetType = AopUtils.getTargetClass(element);
      bindings.put(targetType, element);
      decoratedChain.add(Pair.of(targetType, element));
    }

    doBind(bindings, decoratedChain);
  }

  private void doBind(
      HashMap<Class<?>, Object> bindings, ArrayList<Pair<Class<?>, Object>> decoratedChain) {
    for (val decorated : decoratedChain) {
      doBind(decorated.fst, decorated.snd, bindings);
    }
  }

  private void doBind(Class<?> type, Object instance, HashMap<Class<?>, Object> bindings) {

    if (type.isAnnotationPresent(Decorate.class)) {

      val methods = type.getMethods();
      for (val method : methods) {
        if (method.isAnnotationPresent(Decorate.class)) {
          doBind(method, type, instance, bindings);
        }
      }
    }
  }

  private void doBind(
      Method method, Class<?> type, Object instance, HashMap<Class<?>, Object> bindings) {
    val parameterTypes = method.getParameterTypes();
    val parameterValues = new Object[parameterTypes.length];

    for (int i = 0; i < parameterTypes.length; i++) {
      val parameterType = parameterTypes[i];
      val parameterValue = bindings.get(parameterType);
      checkParam(type, method, parameterType, parameterValue);
      parameterValues[i] = parameterValue;
    }

    try {
      method.invoke(instance, parameterValues);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private void checkParam(Class<?> type, Method method, Class<?> paramType, Object paramValue) {
    if (paramValue == null) {
      throw new IllegalArgumentException(
          format(
              "Failed to decorate method '%s' on type '%s':  no view with type %s available in the view hierarchy",
              method.getName(), type, paramType));
    }
  }
}
