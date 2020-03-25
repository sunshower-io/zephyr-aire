package io.zephyr.aire.api;

import java.lang.annotation.*;

@Documented
@Inherited
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExtensionPoint {

  String urn();

  Mode mode() default Mode.Absolute;

  enum Mode {
    Relative,
    Absolute
  }
}
