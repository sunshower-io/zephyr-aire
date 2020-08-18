package io.zephyr.aire.components.controls;

import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import lombok.val;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HasTooltipTest {

  @Test
  void ensureAddingTooltipAddsClass() {
    val button = new AireButton(new Icon(VaadinIcon.ACADEMY_CAP));
    val tooltip = new AireTooltip("Something about academy caps");
    button.addTooltip(tooltip);
    assertTrue(button.hasClassName("aire-has-tooltip"));
  }

  @Test
  void ensureAddingTooltipAddsTooltipAsChild() {
    val button = new AireButton(new Icon(VaadinIcon.ACADEMY_CAP));
    val tooltip = new AireTooltip("Something about academy caps");
    button.addTooltip(tooltip);
    assertTrue(button.getChildren().anyMatch(e -> e == tooltip));
  }
}