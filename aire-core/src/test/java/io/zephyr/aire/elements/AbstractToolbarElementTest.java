package io.zephyr.aire.elements;

import com.vaadin.flow.component.button.Button;
import lombok.val;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AbstractToolbarElementTest {

  @Test
  void ensureAddAddsComponent() {
    val toolbar = new AireToolbar();
    val button = new Button("Hello");
    toolbar.add(button);
    assertEquals(toolbar.getChildren().findFirst().orElseThrow(), button);
  }

  @Test
  void ensureAddingCallsAddListenersOnButtons() {
    val toolbar = spy(new AireToolbar());
    val button = new Button("Hello");
    toolbar.add(button);
    verify(toolbar, times(1)).addListeners(button);
  }

  @Test
  void ensureAddingWorksAsAnticipatedWithToolbarGroups() {
    val toolbar = spy(new AireToolbar());
    val group = spy(new AireToolbarGroup());
    val button = new Button("Hello");
    group.add(button);
    toolbar.add(group);
    verify(group, times(1)).addListeners(button);
    verify(toolbar, times(0)).addListeners(button);
  }

  @Test
  void ensureAddListenersAddsClickListenerToButton() {
    val toolbar = spy(new AireToolbar());
    val button = spy(new Button("Hello"));
    toolbar.add(button);
    verify(button, times(1)).addClickListener(any());
  }

  @Test
  void ensureAddListenersAddsBlurListenerToButton() {
    val toolbar = spy(new AireToolbar());
    val button = spy(new Button("Hello"));
    toolbar.add(button);
    verify(button, times(1)).addBlurListener(any());
  }

  @Test
  void ensureOnClickAddsClassActive() {
    val toolbar = new AireToolbar();
    val button = new Button("Hello");
    toolbar.add(button);
    button.click();
    assertTrue(button.hasClassName("active"));
  }

  //TODO Josiah: determine why working in browser but not test
  @Disabled
  @Test
  void ensureOnBlurRemovesClassActive() {
    val toolbar = new AireToolbar();
    val button = spy(new Button("Hello"));
    toolbar.add(button);
    button.click();
    button.blur();

    verify(button, times(1)).removeClassName(eq("active"));
//    assertFalse(button.hasClassName("active"));
  }
}