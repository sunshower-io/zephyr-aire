package io.zephyr.aire.components;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.html.Section;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.di.Instantiator;
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
    implements HasElement, HasOrderedComponents, ComponentEventListener<ClickEvent<Button>> {

  /** mutable state */

  /** is the drawer open? */
  private boolean open;

  private Button current;

  private Section contents;
  /** immutable state */
  private final Map<Button, ComponentDescriptor> components;

  public AireAsideDrawerMenu() {
    components = new HashMap<>();
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

  @Override
  public void onComponentEvent(ClickEvent<Button> clickEvent) {

    val source = clickEvent.getSource();
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
      UI.getCurrent()
          .access(
              () -> {
                source.getElement().removeAttribute("active");
                remove(contents);
              });
    }
  }

  private void openDrawer(ComponentDescriptor target, Button source, Button current) {

    val actualContent = instantiate(target);
    UI.getCurrent()
        .access(
            () -> {
              if (contents != null) {
                remove(contents);
              }
              if (current != null) {
                current.getElement().removeAttribute("active");
              }
              source.getElement().setAttribute("active", "true");
              contents = createContents();
              contents.add(actualContent);
              add(contents);
              open = true;
            });
  }

  private Component instantiate(ComponentDescriptor target) {
    if (target.instance != null) {
      return target.instance;
    }
    return Instantiator.get(UI.getCurrent()).createComponent(target.componentType);
  }

  protected Section createContents() {
    val contents = new Section();
    contents.getElement().setAttribute("slot", "content");
    return contents;
  }

  @AllArgsConstructor
  static class ComponentDescriptor {
    final Component instance;
    final Class<? extends Component> componentType;
  }

  private Button decorateButton(Button button) {
    button.getElement().setAttribute("slot", "menu");
    add(button);
    button.addClickListener(this);
    return button;
  }
}
