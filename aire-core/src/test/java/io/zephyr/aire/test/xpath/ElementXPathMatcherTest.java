package io.zephyr.aire.test.xpath;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.*;
import io.zephyr.aire.test.Element;
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
  void ensureNestedSelectWorks() {
    val root = new Div();
    val first = new Div();
    root.add(first);
    val second = new Div();
    first.add(second);

    val fstmatch = new Div();
    fstmatch.addClassName("aire-bean-form");
    second.add(fstmatch);

    val sndMatch = new Span();
    sndMatch.addClassName("aire-bean-form");
    fstmatch.add(sndMatch);

    val query = "//*[@class='aire-bean-form']/*[@class='aire-bean-form']";
    val result = new ElementXPathMatcher(query).select(root);
    assertTrue(sndMatch == result);
  }

  @Test
  void ensureRootSelectAllWorks() {
    val fst = new Div();
    val snd = new Div();
    val thrd = new Div();
    fst.add(snd);
    snd.add(thrd);

    assertEquals(new ElementXPathMatcher("//div").match(fst).size(), 3);
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
  void ensureDirectChildIsSelectable() {
    val expr = new ElementXPathMatcher("//*[@class='aire-bean-form']/*[@class='aire-bean-form']");
    val body = new Div();

    val container = new Div();
    container.addClassName("aire-bean-form");
    val intermediateChild = new Div();
    intermediateChild.addClassName("aire-bean-form");

    container.add(intermediateChild);
    body.add(container);
    val results = expr.match(body);
    assertEquals(results.get(results.size() - 1), intermediateChild);
  }

  @Test
  void ensureNestedScenarioWorks() {

      //todo
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
  void ensureNestedElementWithClassSelectorCanBeSelected() {
    val root = new Div();
    val firstLevel = new Div();
    root.add(firstLevel);

    val secondLevel = new Div();
    firstLevel.add(secondLevel);

    val firstBeanForm = new Div();
    secondLevel.add(firstBeanForm);
    firstBeanForm.addClassName("aire-bean-form");

    val buttonGroupContainer = new Div();
    val firstChild = new Button();
    val secondChild = new Button();

    buttonGroupContainer.addClassName("aire-button-group");
    buttonGroupContainer.add(firstChild);
    buttonGroupContainer.add(secondChild);

    firstBeanForm.add(buttonGroupContainer);

    val query = "//*[@class='aire-bean-form']/div[@class='aire-button-group']";
    val matched = new ElementXPathMatcher(query).select(root);
    assertTrue(matched instanceof Div);
    assertEquals(matched.getElement().getChildCount(), 2);
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
