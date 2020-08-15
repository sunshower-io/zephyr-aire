package io.zephyr.aire.components.controls;

import io.zephyr.aire.components.controls.AireIcon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AireIconTest {

    @Test
    void ensureParsingNameWorks() {
        assertEquals("hello-world", AireIcon.parseName("HelloWorld"));
    }

}