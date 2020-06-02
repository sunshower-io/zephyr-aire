package io.zephyr.aire.test;

import io.zephyr.aire.test.core.Constants;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface EditView {

  /** @return the fields to include in the decoration context */
  String[] withFields() default Constants.NONE;

  /**
   * a specific field to include in the decoration context
   *
   * @return
   */
  String withField() default Constants.NONE;

  /**
   * @return the name of a specific method whose result should be included in the decoration context
   */
  String withMethod() default Constants.NONE;

  /** @return the names of methods whose results should be included in the decoration context */
  String[] withMethods() default Constants.NONE;
}
