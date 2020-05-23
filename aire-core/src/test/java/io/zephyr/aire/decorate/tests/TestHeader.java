package io.zephyr.aire.decorate.tests;

import io.zephyr.aire.api.Slot;
import io.zephyr.aire.elements.AireHeader;
import lombok.Getter;
import lombok.Setter;

public class TestHeader extends AireHeader {

  @Getter
  @Slot(":left")
  private Leaf left;

  @Getter
  @Slot(":right")
  private Leaf right;

  public void setLeft(Leaf left) {
    this.left = left;
    this.add(left);
  }

  public void setRight(Leaf right) {
    this.right = right;
    this.add(right);
  }
}
