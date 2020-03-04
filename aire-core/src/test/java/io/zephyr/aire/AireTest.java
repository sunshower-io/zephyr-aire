package io.zephyr.aire;

import io.sunshower.kernel.test.ZephyrTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Documented
@Inherited
@ZephyrTest
@SpringBootTest
@Retention(RetentionPolicy.RUNTIME)
public @interface AireTest {}
