package io.zephyr.aire.annotation;

import io.zephyr.aire.api.ExtensionPoint;
import io.zephyr.aire.api.ExtensionPointDefinition;
import io.zephyr.aire.ext.DefaultExtensionPointDefinition;
import io.zephyr.aire.ext.MutableExtensionPointRegistry;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.context.annotation.Bean;

import java.beans.IntrospectionException;
import java.beans.Introspector;

import static java.lang.String.format;

@Slf4j
public class ExtensionPointScanner {

  final MutableExtensionPointRegistry registry;

  public ExtensionPointScanner(MutableExtensionPointRegistry registry) {
    this.registry = registry;
  }

  public <T> ExtensionPointDefinition<T> scan(Class<T> type, ExtensionPoint extensionPoint)
      throws IntrospectionException {

    return doScan(null, type, extensionPoint);
  }

  private <T> ExtensionPointDefinition<T> doScan(
      ExtensionPoint parent, Class<T> type, ExtensionPoint extensionPoint)
      throws IntrospectionException {

    log.info("Scanning extension point type {}", type);
    try {

      val parentName = resolveParentName(parent);
      val name = resolveAbsoluteName(parentName, extensionPoint);
      if (registry.containsDefinition(name)) {
        return null;
      }

      val definition = new DefaultExtensionPointDefinition<T>(type, name);

      val parentDefinition = type.getAnnotation(ExtensionPoint.class);

      scanMethods(parentDefinition, type);
      scanFields(parentDefinition, type);

      registry.register(definition);
      return definition;

    } catch (IntrospectionException ex) {
      log.warn("Failed to introspect type {}.  Reason: {}", type, ex.getMessage());
      throw ex;
    }
  }

  private String resolveParentName(ExtensionPoint parentLocation) {
    if (parentLocation == null) {
      return null;
    }
    if (!void.class.equals(parentLocation.parent())) {
      val pclass = parentLocation.parent();
      return pclass.getAnnotation(ExtensionPoint.class).location();
    }

    if (!"__CLASS__".equals(parentLocation.location())) {
      return parentLocation.location();
    }

    return null;
  }

  private String resolveAbsoluteName(String parentName, ExtensionPoint extensionPoint) {
    val location = extensionPoint.location();
    if (parentName == null) {
      return location;
    }
    if (location.charAt(0) == ':') {
      return location;
    }
    return String.format("%s:%s", parentName, location);
  }

  private void scanFields(ExtensionPoint parentDefinition, Class<?> type)
      throws IntrospectionException {
    for (var c = type; c != Object.class; c = c.getSuperclass()) {
      for (val field : c.getDeclaredFields()) {
        if (field.isAnnotationPresent(ExtensionPoint.class)) {
          doScan(parentDefinition, field.getType(), field.getAnnotation(ExtensionPoint.class));
        }
      }
    }
  }

  private <T> void scanMethods(ExtensionPoint parentDefinition, Class<T> type)
      throws IntrospectionException {
    val info = Introspector.getBeanInfo(type);

    for (val propertyDescriptor : info.getPropertyDescriptors()) {
      val readMethod = propertyDescriptor.getReadMethod();

      if (readMethod != null) {
        if (readMethod.isAnnotationPresent(ExtensionPoint.class)) {
          doScan(
              parentDefinition,
              readMethod.getReturnType(),
              readMethod.getAnnotation(ExtensionPoint.class));
        }
      }

      val writeMethod = propertyDescriptor.getWriteMethod();

      if (writeMethod != null) {

        val types = writeMethod.getParameterTypes();

        for (val t : types) {
          if (t.isAnnotationPresent(ExtensionPoint.class)) {
            doScan(parentDefinition, t, t.getAnnotation(ExtensionPoint.class));
          }
        }
      }
    }

    Introspector.flushFromCaches(type);
  }
}
