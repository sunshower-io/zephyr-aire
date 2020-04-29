package io.zephyr.aire.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface Slot {
  String value();

  Mode mode() default Mode.APPEND;

  enum Mode {
    /** if set, append to existing element */
    APPEND,

    /** if set, replace existing element */
    REPLACE
  }
}
