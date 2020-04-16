package io.zephyr.aire.test;

import org.springframework.test.context.ContextConfiguration;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@AireTest
@Documented
@Retention(RetentionPolicy.RUNTIME)
@ContextConfiguration(classes = AireServletTestConfiguration.class)
public @interface AireServletTest {}
