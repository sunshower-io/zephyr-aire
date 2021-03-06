package io.zephyr.aire.test.xpath;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import lombok.val;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElementXPathMatcherTest {

  @Test
  void ensureRootSelectionWorks() {
    val results = new ElementXPathMatcher("/h1").match(new H1());
    assertEquals(results.size(), 1);
  }

  @Test
  void ensureRepetitionWorks() {
    val results = new ElementXPathMatcher("/h1/h2/h3");
    val el = new H1();
    val ch = new H2();
    val gch = new H3();
    ch.add(gch);
    el.add(ch);

    val eles = results.match(el);
    assertEquals(eles.size(), 1);
  }

  @Test
  void ensureAnyAttributeWorks() {
    val m = new ElementXPathMatcher("/div/h1[@*]");

    val fst = new H1();
    fst.getElement().setAttribute("hello", "world");
    val snd = new H1();

    val div = new Div();
    div.add(fst, snd);

    val results = m.match(div);
    assertEquals(results.size(), 1);
  }

  @Test
  void ensureAnyAttributeMatcherWorks() {
    val m = new ElementXPathMatcher("/div/h1[@hello='world']");

    val fst = new H1();
    fst.getElement().setAttribute("hello", "world");
    val snd = new H1();

    val div = new Div();
    div.add(fst, snd);

    val results = m.match(div);
    assertEquals(results.size(), 1);
  }

  @Test
  void ensureMatchingOnAnyTagWithAttributeValueInDocumentWorks() {
    val m = new ElementXPathMatcher("//*[@hello='world']");
    val root = new Div();
    val d1 = new Div();
    val d2 = new Div();
    val d3 = new Div();
    d2.getElement().setAttribute("hello", "world");

    root.add(d1);
    d1.add(d2);
    d2.add(d3);

    val results = m.match(root);
    assertEquals(results.size(), 1);
  }

  @Test
  void ensureDocumentSelectionWorks() {
    val div = new Div();
    val fst = new H1();
    val snd = new H1();

    div.add(fst);
    var child = new Div();
    child.add(snd);

    div.add(child);

    var child2 = new Div();

    val third = new H1();
    child2.add(third);
    child.add(child2);

    val results = new ElementXPathMatcher("//h1").match(div);
    assertEquals(results.size(), 3);
    assertTrue(results.stream().allMatch(t -> t.getElement().getTag().equals("h1")));
  }
}
