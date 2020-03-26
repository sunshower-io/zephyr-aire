package io.zephyr.aire.api;

import java.lang.annotation.*;

@Documented
@Inherited
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExtensionPoint {

  /**
   * The parent location of this extension point. If this annotation is present on a property, i.e.
   *
   * <p>1. a field 2. a bean method (get/set)
   *
   * <p>and the location does not begin with a colon (":") then the parent is defined to be the host
   * class.
   */
  Class<?> parent() default void.class;

  String parentLocation() default "__CLASS__";

  /**
   * @return the location of this extension point within the hierarchy
   *     <p>an extension point is considered to be absolute if it begins with a colon ":" and
   *     relative if it does not, in which case it is resolved to its parent extension point
   */
  String location();

}
