package io.zephyr.aire.test;

import io.sunshower.kernel.test.ZephyrTest;
import io.zephyr.aire.AireConfiguration;
import io.zephyr.aire.test.core.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
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
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith(ViewContextParameterResolver.class)
// this must come after ViewContextParameterResolver
@ExtendWith(EditViewParameterResolver.class)
@ExtendWith({MockitoExtension.class, ContextParameterResolver.class, ViewMockingExtenstion.class})
@ContextConfiguration(classes = {AireConfiguration.class, AireTestConfiguration.class})
@TestExecutionListeners(
    listeners = ApplicationTrackerListener.class,
    mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
public @interface AireTest {}
