package io.zephyr.aire.test;

import io.zephyr.aire.test.core.AireServletTestConfiguration;
import org.springframework.test.context.ContextConfiguration;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@AireTest
@Documented
@Retention(RetentionPolicy.RUNTIME)
@ContextConfiguration(classes = AireServletTestConfiguration.class)
public @interface AireServletTest {}
