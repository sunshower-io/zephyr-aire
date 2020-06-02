package io.zephyr.aire.test;

import io.zephyr.aire.AireConfiguration;
import io.zephyr.aire.test.core.AireServletTestConfiguration;
import io.zephyr.aire.test.core.AireTestConfiguration;
import org.springframework.test.context.ContextConfiguration;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@AireTest
@Documented
@Retention(RetentionPolicy.RUNTIME)
@ContextConfiguration(
    classes = {
      AireConfiguration.class,
      AireTestConfiguration.class,
      AireServletTestConfiguration.class
    })
public @interface AireServletTest {}
