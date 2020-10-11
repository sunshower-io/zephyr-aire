package io.zephyr.aire.test.vxpath;

import com.vaadin.flow.component.html.Div;
import lombok.val;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class XPathQueryExpressionTest {

  static QueryExpression compile(String expr) {
    return new XPathQueryExpression(expr);
  }

  @Test
  void ensureMatchingSingleNameWorks() {
    val root = new Div();
    assertEquals(compile("/div").select(root).get(0), root);
  }
}
