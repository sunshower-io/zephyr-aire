package io.zephyr.aire.reflect;

import lombok.val;

import java.beans.Introspector;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.function.Function;
import java.util.regex.Pattern;

public class Reflection {

  private static final Pattern MUTATOR_PREFIX = Pattern.compile("^(set|get|is)");
  private static final Pattern GETTER_PREFIX = Pattern.compile("^(get)\\p{Lu}");
  private static final Pattern BOOLEAN_GETTER_PREFIX = Pattern.compile("^(is)\\p{Lu}");
  private static final Pattern SETTER_PREFIX = Pattern.compile("^set\\p{Lu}");

  public static <T> void traverseProperties(
      Iterable<Class<?>> traversal,
      Class<? extends Annotation> annotation,
      Function<? super Annotation, String> aliasExtractor,
      PropertyTraversalCallback<T> callback) {

    callback.onTraversalStart();

    for (val type : traversal) {
      callback.onRootOpen(type);
      val propertyDescriptors = new HashMap<String, UnreconciledPropertyDescriptor>();

      collectFields(type, propertyDescriptors, annotation, aliasExtractor);
      collectMethods(type, propertyDescriptors, annotation, aliasExtractor);

      val iter = propertyDescriptors.entrySet().iterator();
      while (iter.hasNext()) {
        val next = iter.next();

        val unreconciledPropertyDescriptor = next.getValue();
        val ann = getAnnotation(unreconciledPropertyDescriptor);

        if (ann != null) {
          PropertyDescriptor descriptor = null;
          if (!(unreconciledPropertyDescriptor.field == null
              || unreconciledPropertyDescriptor.getter == null
              || unreconciledPropertyDescriptor.setter == null)) {
            descriptor =
                new CompletePropertyDescriptor(
                    getName(unreconciledPropertyDescriptor),
                    getAlias(unreconciledPropertyDescriptor),
                    getType(unreconciledPropertyDescriptor),
                    unreconciledPropertyDescriptor.field,
                    unreconciledPropertyDescriptor.getter,
                    unreconciledPropertyDescriptor.setter);
          } else if (!(unreconciledPropertyDescriptor.getter == null
              || unreconciledPropertyDescriptor.setter == null)) {
            descriptor =
                new MutatorExclusivePropertyDescriptor(
                    getName(unreconciledPropertyDescriptor),
                    getAlias(unreconciledPropertyDescriptor),
                    getType(unreconciledPropertyDescriptor),
                    unreconciledPropertyDescriptor.getter,
                    unreconciledPropertyDescriptor.setter);
          } else if (!(unreconciledPropertyDescriptor.setter == null
              || unreconciledPropertyDescriptor.field == null)) {
            descriptor =
                new MutateBySetterRetrieveByFieldPropertyDescriptor(
                    getName(unreconciledPropertyDescriptor),
                    getAlias(unreconciledPropertyDescriptor),
                    getType(unreconciledPropertyDescriptor),
                    unreconciledPropertyDescriptor.field,
                    unreconciledPropertyDescriptor.setter);
          } else if (!(unreconciledPropertyDescriptor.getter == null
              || unreconciledPropertyDescriptor.field == null)) {
            descriptor =
                new MutateByFieldRetrieveByGetterPropertyDescriptor(
                    getName(unreconciledPropertyDescriptor),
                    getAlias(unreconciledPropertyDescriptor),
                    getType(unreconciledPropertyDescriptor),
                    unreconciledPropertyDescriptor.field,
                    unreconciledPropertyDescriptor.getter);
          } else if (unreconciledPropertyDescriptor.field != null) {
            descriptor =
                new FieldAccessPropertyDescriptor(
                    getName(unreconciledPropertyDescriptor),
                    getAlias(unreconciledPropertyDescriptor),
                    getType(unreconciledPropertyDescriptor),
                    unreconciledPropertyDescriptor.field);
          } else {
            callback.onInvalidProperty(type, next.getKey(), annotation);
          }

          callback.onProperty(descriptor);
        }
      }
      callback.onRootClose(type);
    }
    callback.onTraversalComplete();
  }

  public static <T> void traverseProperties(
      Class<?> startClass,
      Class<?> stopClass,
      Class<? extends Annotation> annotation,
      Function<? super Annotation, String> aliasExtractor,
      PropertyTraversalCallback<T> callback) {
    traverseProperties(
        new ClassHierarchy(startClass, stopClass), annotation, aliasExtractor, callback);
  }

  public static AnnotatedPropertySet getAnnotatedProperties(
      Class<?> type,
      Class<? extends Annotation> annotation,
      Function<? super Annotation, String> aliasExtractor) {
    val callback = new AnnotatedPropertySetProducingPropertyTraversalCallback();
    traverseProperties(type, type.getSuperclass(), annotation, aliasExtractor, callback);
    return callback.getResult();
  }

  private static String getAlias(UnreconciledPropertyDescriptor unreconciledPropertyDescriptor) {
    return unreconciledPropertyDescriptor.alias;
  }

  static Class<?> getType(UnreconciledPropertyDescriptor descriptor) {
    if (descriptor.fieldType != null) {
      return descriptor.fieldType;
    }

    if (descriptor.getterType != null) {
      return descriptor.getterType;
    }

    if (descriptor.setterType != null) {
      return descriptor.setterType;
    }
    throw new IllegalArgumentException(
        "Error: could not infer type of property.  Definitely a bug");
  }

  static String getName(UnreconciledPropertyDescriptor descriptor) {
    if (descriptor.name != null) {
      return descriptor.name;
    }

    if (descriptor.predictedGetterName != null) {
      return fieldNameFor(descriptor.getter);
    }

    if (descriptor.predictedSetterName != null) {
      return fieldNameFor(descriptor.setter);
    }
    throw new IllegalArgumentException("Could not infer name");
  }

  static Annotation getAnnotation(UnreconciledPropertyDescriptor descriptor) {
    val fieldAnnotation = descriptor.fieldAnnotation;
    val getterAnnotation = descriptor.getterAnnotation;
    val setterAnnotation = descriptor.setterAnnotation;
    if (getterAnnotation != null) {
      return getterAnnotation;
    }

    if (setterAnnotation != null) {
      return setterAnnotation;
    }

    if (fieldAnnotation != null) {
      return fieldAnnotation;
    }
    return null;
  }

  public static String toGetter(String fieldName, Class<?> type) {
    if (boolean.class.equals(type) || Boolean.class.equals(type)) {
      return prefix("is", fieldName);
    }
    return prefix("get", fieldName);
  }

  public static String toSetter(String fieldName) {
    return prefix("set", fieldName);
  }

  private static String prefix(String prefix, String fieldName) {
    return prefix + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
  }

  private static void collectFields(
      Class<?> type,
      HashMap<String, UnreconciledPropertyDescriptor> propertyDescriptors,
      Class<? extends Annotation> annotationType,
      Function<? super Annotation, String> aliasExtractor) {
    for (val field : type.getDeclaredFields()) {
      if (!field.isSynthetic()) {
        val descriptor = new UnreconciledPropertyDescriptor();
        descriptor.name = field.getName();
        descriptor.fieldType = field.getType();
        descriptor.field = field;
        descriptor.predictedSetterName = toSetter(descriptor.name);
        descriptor.predictedGetterName = toGetter(descriptor.name, descriptor.fieldType);

        if (field.isAnnotationPresent(annotationType)) {
          descriptor.fieldAnnotation = field.getAnnotation(annotationType);
          descriptor.alias = aliasExtractor.apply(descriptor.fieldAnnotation);
        }
        propertyDescriptors.put(field.getName(), descriptor);
      }
    }
  }

  private static void collectMethods(
      Class<?> type,
      HashMap<String, UnreconciledPropertyDescriptor> propertyDescriptors,
      Class<? extends Annotation> annotation,
      Function<? super Annotation, String> aliasExtractor) {
    for (val method : type.getDeclaredMethods()) {
      if (!method.isSynthetic()) {

        val setter = isSetter(method);
        val getter = isGetter(method);

        if (setter || getter) {
          val predictedFieldName = fieldNameFor(method);

          var propertyDescriptor = propertyDescriptors.get(predictedFieldName);

          if (propertyDescriptor == null) {
            propertyDescriptor =
                getPropertyDescriptor(
                    annotation, method, setter, predictedFieldName, aliasExtractor);
            propertyDescriptors.put(predictedFieldName, propertyDescriptor);
          } else {
            if (setter) {
              getSetter(annotation, method, predictedFieldName, propertyDescriptor, aliasExtractor);
            } else {
              getGetter(annotation, method, predictedFieldName, propertyDescriptor, aliasExtractor);
            }
          }
        }
      }
    }
  }

  private static UnreconciledPropertyDescriptor getPropertyDescriptor(
      Class<? extends Annotation> annotation,
      Method method,
      boolean setter,
      String predictedFieldName,
      Function<? super Annotation, String> aliasExtractor) {
    UnreconciledPropertyDescriptor propertyDescriptor;
    propertyDescriptor = new UnreconciledPropertyDescriptor();
    if (setter) {
      getSetter(annotation, method, predictedFieldName, propertyDescriptor, aliasExtractor);

    } else {
      getGetter(annotation, method, predictedFieldName, propertyDescriptor, aliasExtractor);
    }
    return propertyDescriptor;
  }

  private static void getGetter(
      Class<? extends Annotation> annotation,
      Method method,
      String predictedFieldName,
      UnreconciledPropertyDescriptor propertyDescriptor,
      Function<? super Annotation, String> aliasExtractor) {
    propertyDescriptor.predictedSetterName = toSetter(predictedFieldName);
    propertyDescriptor.getter = method;
    propertyDescriptor.getterType = method.getReturnType();

    if (method.isAnnotationPresent(annotation)) {
      propertyDescriptor.getterAnnotation = method.getAnnotation(annotation);
      propertyDescriptor.alias = aliasExtractor.apply(propertyDescriptor.getterAnnotation);
    }
  }

  private static void getSetter(
      Class<? extends Annotation> annotation,
      Method method,
      String predictedFieldName,
      UnreconciledPropertyDescriptor propertyDescriptor,
      Function<? super Annotation, String> aliasExtractor) {
    propertyDescriptor.predictedGetterName = toGetter(predictedFieldName, method.getReturnType());
    propertyDescriptor.setter = method;
    propertyDescriptor.setterType = method.getParameterTypes()[0];

    if (method.isAnnotationPresent(annotation)) {
      propertyDescriptor.setterAnnotation = method.getAnnotation(annotation);
      propertyDescriptor.alias = aliasExtractor.apply(propertyDescriptor.setterAnnotation);
    }
  }

  public static String fieldNameFor(Method m) {
    val name = m.getName();
    return Introspector.decapitalize(MUTATOR_PREFIX.matcher(name).replaceFirst(""));
  }

  public static boolean isMutator(Method method) {
    val paramCount = method.getParameterCount();

    if (paramCount > 1) {
      return false;
    }
    return isGetter(method) || isSetter(method);
  }

  public static boolean isGetter(Method m) {
    val name = m.getName();
    return m.getParameterCount() == 0
        && (GETTER_PREFIX.matcher(name).find() || BOOLEAN_GETTER_PREFIX.matcher(name).find());
  }

  public static boolean isSetter(Method m) {
    return m.getParameterCount() == 1
        && m.getReturnType() == Void.TYPE
        && SETTER_PREFIX.matcher(m.getName()).find();
  }

  @SuppressWarnings("unchecked")
  public static <T, U> U fieldValue(String fieldName, Class<T> type, T instance) {
    try {
      val field = type.getDeclaredField(fieldName);
      return (U) field.get(instance);
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }

  static class UnreconciledPropertyDescriptor {

    private String alias;
    private String name;
    private Field field;
    private Method getter;
    private Method setter;
    private Class<?> fieldType;
    private Class<?> getterType;
    private Class<?> setterType;
    private String predictedSetterName;
    private String predictedGetterName;
    private Annotation fieldAnnotation;
    private Annotation getterAnnotation;
    private Annotation setterAnnotation;
  }

  static final class AnnotatedPropertySetProducingPropertyTraversalCallback
      implements PropertyTraversalCallback<AnnotatedPropertySet> {
    final MutableAnnotatedPropertySet result;

    AnnotatedPropertySetProducingPropertyTraversalCallback() {
      result = new MutableAnnotatedPropertySet();
    }

    @Override
    public void onInvalidProperty(
        Class<?> owner, String propertyName, Class<? extends Annotation> annotationType) {
      throw new IllegalArgumentException(
          String.format(
              "Somehow all of (field, getter, setter) were null for property %s on type %s (searching for annotation %s)",
              propertyName, owner, annotationType));
    }

    @Override
    public void onProperty(PropertyDescriptor descriptor) {
      result.add(descriptor);
    }

    @Override
    public AnnotatedPropertySet getResult() {
      return result;
    }
  }
}
