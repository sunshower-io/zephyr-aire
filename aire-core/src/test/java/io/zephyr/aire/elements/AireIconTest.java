package io.zephyr.aire.elements;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AireIconTest {

    @Test
    void ensureParsingNameWorks() {
        assertEquals("hello-world", AireIcon.parseName("HelloWorld"));
    }

}