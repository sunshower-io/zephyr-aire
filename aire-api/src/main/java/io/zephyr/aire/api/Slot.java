package io.zephyr.aire.api;

import java.lang.annotation.*;

@Inherited
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Slot {
  Class<? extends Placement> value();
}
