package io.zephyr.aire.components.controls;

import io.zephyr.aire.components.controls.AireIcon;
import io.zephyr.aire.components.controls.AireToggleButton;
import lombok.val;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AireToggleButtonTest {

    @Test
    void ensureButtonHasCorrectSelectors() {
        val button = new AireToggleButton(AireIcon.icon("seedling"));
        assertEquals("vaadin-button", button.getElement().getTag());
        assertEquals("aire-toggle-button", button.getClassName());
    }

    @Test
    void ensureActiveGetsAddedWhenClicked() {
        val button = new AireToggleButton(AireIcon.icon("seedling"));
        button.click();
        assertTrue(button.hasClassName("active"));
    }

    @Test
    void ensureActiveGetsRemovedWhenReClicked() {
        val button = new AireToggleButton(AireIcon.icon("seedling"));
        button.click();
        button.click();
        assertFalse(button.hasClassName("active"));
    }
}