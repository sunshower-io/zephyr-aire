package io.zephyr.aire.test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ScanRoutes {
  String value() default "__DEFAULT__";
}
