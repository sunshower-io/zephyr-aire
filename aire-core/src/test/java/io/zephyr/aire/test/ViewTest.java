package io.zephyr.aire.test;

import com.vaadin.flow.component.Component;
import org.junit.jupiter.api.Test;
import java.lang.annotation.*;

@Test
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface ViewTest {

  Class<? extends Component>[] value() default {};

  Class<? extends Component> active() default Component.class;
}
