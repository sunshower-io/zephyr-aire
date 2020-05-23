package io.zephyr.aire.decorate.tests;

import io.zephyr.aire.api.Container;
import io.zephyr.aire.api.Slot;
import io.zephyr.aire.decorate.tests.TestComponentRoot;
import io.zephyr.aire.decorate.tests.TestHeader;

@Container(":whatever")
class TestComponentChild extends TestComponentRoot {

  @Slot(":footer")
  private TestHeader footer;
}
