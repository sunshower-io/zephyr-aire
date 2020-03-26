package io.zephyr.aire.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Extension {

  /**
   * mode = append
   *
   * @return the order of this component in the target widget
   */
  int order() default Integer.MAX_VALUE;

  /**
   *
   * a globally-unique ID for this extension.  If unspecified, the framework
   * will generate one
   */
  String id() default "__GENERATED__";

  /**
   * which location(s) are to be decorated by this?
   *
   * @return
   */
  String[] targets() default "__VALUE__";

  /** alias for target() */
  String[] value() default "__VALUE__";
}
