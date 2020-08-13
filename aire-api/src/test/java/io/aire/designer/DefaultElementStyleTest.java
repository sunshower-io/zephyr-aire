package io.aire.designer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefaultElementStyleTest {

  @Test
  void ensureElementStyleProducesCorrectRepresentationForSingleStyle() {
    var style = ElementStyles.newStyle().set("outlineConnect", "0").set("fontColor", "#220022");
    assertEquals(style.toString(), "outlineConnect=0;fontColor=#220022;");
  }
}
