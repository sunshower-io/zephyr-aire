package io.zephyr.aire.test;

import io.sunshower.kernel.test.ZephyrTest;
import io.zephyr.aire.test.AireTestConfiguration;
import io.zephyr.aire.test.RouteScanningTestExecutionListener;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.web.WebAppConfiguration;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Documented
@Inherited
@ZephyrTest
@SpringBootTest
@WebAppConfiguration
@ContextConfiguration(classes = AireTestConfiguration.class)
@Retention(RetentionPolicy.RUNTIME)
@TestExecutionListeners(
        listeners = RouteScanningTestExecutionListener.class,
        mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
public @interface AireTest {}
