package io.zephyr.aire.decorate.tests;

import io.zephyr.aire.api.Container;
import io.zephyr.aire.api.Editable;
import io.zephyr.aire.api.Slot;

@Container(":root")
public class TestComponentRoot implements Editable {

  @Slot(":header")
  private TestHeader header;

    @Override
    public boolean canApply(int operation) {
        return false;
    }

    @Override
    public void apply(int operation, Object instance) {

    }
}
