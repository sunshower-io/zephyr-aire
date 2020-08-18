package io.zephyr.aire.components.controls;

import lombok.val;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AireTooltipTest {

  @Test
  void ensureTooltipHasAPositionByDefault() {
    val tooltip = new AireTooltip("boop");

    assertTrue(tooltip.hasClassName("aire-tooltip-horizontal-right"));
    assertTrue(tooltip.hasClassName("aire-tooltip-vertical-bottom"));
  }

  @Test
  void ensureSetHorizontalPositionSetsNewHorizontalPosition() {
    val tooltip = new AireTooltip("boop");
    tooltip.setHorizontalPosition(AireTooltip.Horizontal.LEFT);
    assertTrue(tooltip.hasClassName("aire-tooltip-horizontal-left"));
  }

  @Test
  void ensureSetHorizontalPositionRemovesOldHorizontalPosition() {
    val tooltip = new AireTooltip("boop");
    tooltip.setHorizontalPosition(AireTooltip.Horizontal.LEFT);
    assertFalse(tooltip.hasClassName("aire-tooltip-horizontal-right"));
  }

  @Test
  void ensureSetVerticalPositionSetsNewVerticalPosition() {
    val tooltip = new AireTooltip("boop");
    tooltip.setVerticalPosition(AireTooltip.Vertical.MIDDLE);
    assertTrue(tooltip.hasClassName("aire-tooltip-vertical-middle"));
  }

  @Test
  void ensureSetVerticalPositionRemovesOldVerticalPosition() {
    val tooltip = new AireTooltip("boop");
    tooltip.setVerticalPosition(AireTooltip.Vertical.MIDDLE);
    assertFalse(tooltip.hasClassName("aire-tooltip-vertical-bottom"));
  }

  @Test
  void ensureSetPositionSetsBothHorizontalAndVertical() {
    val tooltip = new AireTooltip("boop");
    tooltip.setPosition(AireTooltip.Horizontal.MIDDLE, AireTooltip.Vertical.MIDDLE);
    val classNames = tooltip.getClassNames();

    assertTrue(classNames.contains("aire-tooltip-horizontal-middle"));
    assertTrue(classNames.contains("aire-tooltip-vertical-middle"));
  }
}