package io.zephyr.aire;

import io.zephyr.kernel.core.ModuleCoordinate;
import lombok.val;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoordinatesTest {

  @Test
  void ensureCoordinateIsCorrect() {
    val coordinate =
        ModuleCoordinate.create("io.sunshower.whatever", "sunshower-test", "1.0.0-SNAPSHOT");
    val fragment = Coordinates.toFragment(coordinate);
    assertEquals("io/sunshower/whatever/sunshower-test/1/0/0-SNAPSHOT", fragment, "must be equal");
  }
}
