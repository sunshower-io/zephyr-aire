package io.zephyr.aire.test;

import io.zephyr.aire.test.core.Constants;

import java.lang.annotation.*;

@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface Element {
  String path() default Constants.NONE;

  String value() default Constants.NONE;
}
