package io.zephyr.aire.components;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.html.Nav;
import com.vaadin.flow.component.html.Section;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.di.Instantiator;
import io.aire.core.AireComponent;
import lombok.AllArgsConstructor;
import lombok.val;

import java.util.HashMap;
import java.util.Map;

@Tag("aire-aside")
@JsModule("lit-element")
@NpmPackage(value = "lit-element", version = "^2.3.1")
@JsModule("./components/aire/aire-aside-drawer.ts")
@CssImport("./styles/aire/components/aire-drawer.css")
@CssImport("./styles/aire/components/aire-aside-drawer.css")
public class AireAsideDrawerMenu extends Component
    implements HasElement,
        AireComponent,
        HasOrderedComponents,
        ComponentEventListener<ClickEvent<Button>> {

  /** mutable state */

  /** is the drawer open? */
  private boolean open;

  private Button current;

  private Section contents;
  /** immutable state */
  private final Map<Button, ComponentDescriptor> components;

  private final ComponentEventListener<ClickEvent<Button>> closeListener;

  public AireAsideDrawerMenu() {
    components = new HashMap<>();
    closeListener =
        (ComponentEventListener<ClickEvent<Button>>)
            buttonClickEvent -> close(buttonClickEvent.getSource());
    getElement().getClassList().add("aire-aside");
  }

  public void add(Button button, Component component) {
    val btn = decorateButton(button);
    components.put(btn, new ComponentDescriptor(component, null));
  }

  public void add(Button button, Class<? extends Component> type) {
    val btn = decorateButton(button);
    components.put(btn, new ComponentDescriptor(null, type));
  }

  public void activate(Button button) {
    doActivate(button);
  }

  public void setContent(Button button, Component component) {
    val source = components.get(button);
    source.instance = component;
    if (source.componentType != null) {
      source.componentType = null;
    }
    doActivate(button);
  }

  public void setContent(Button button, Class<? extends Component> component) {
    val source = components.get(button);
    source.componentType = component;
    if (source.instance != null) {
      source.instance = null;
    }
    doActivate(button);
  }

  @Override
  public void onComponentEvent(ClickEvent<Button> clickEvent) {
    val source = clickEvent.getSource();
    doActivate(source);
  }

  private void doActivate(Button source) {
    if (open && source == current) {
      close(source);
      current = null;
    } else {
      val target = components.get(source);
      openDrawer(target, source, current);
      current = source;
    }
  }

  private void close(Button source) {
    if (contents != null) {
      open = false;
      access(() -> doClose(source));
    }
  }

  private void doClose(Button source) {
    source.getElement().removeAttribute("active");
    remove(contents);
  }

  private void openDrawer(ComponentDescriptor target, Button source, Button current) {
    val actualContent = instantiate(target);
    access(() -> doOpen(source, current, actualContent));
  }

  private void doOpen(Button source, Button current, Component actualContent) {
    if (contents != null) {
      remove(contents);
    }
    if (current != null) {
      current.getElement().removeAttribute("active");
    }
    source.getElement().setAttribute("active", "true");
    contents = createContents(source);
    contents.add(actualContent);
    add(contents);
    open = true;
  }

  private Component instantiate(ComponentDescriptor target) {
    if (target.instance != null) {
      return target.instance;
    }
    return Instantiator.get(UI.getCurrent()).createComponent(target.componentType);
  }

  protected Section createContents(Button source) {
    val contents = new Section();
    contents.add(createNav(source));
    contents.getElement().setAttribute("slot", "content");
    return contents;
  }

  private Nav createNav(Button source) {
    val nav = new Nav();
    nav.addClassNames("aire-nav");
    val title = new Span(source.getText());
    val close = new Button();
    close.setIcon(new Icon(VaadinIcon.CLOSE_BIG));
    close.addClickListener(closeListener);
    nav.add(close);
    nav.add(title);
    return nav;
  }

  @AllArgsConstructor
  static final class ComponentDescriptor {
    private Component instance;
    private Class<? extends Component> componentType;
  }

  private Button decorateButton(Button button) {
    button.getElement().setAttribute("slot", "menu");
    add(button);
    button.addClickListener(this);
    return button;
  }
}
