package io.zephyr.aire.layout;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.router.RouterLayout;
import io.aire.core.AireContainer;
import io.aire.core.AireLayout;
import io.zephyr.aire.api.ExtensionPoint;
import io.zephyr.aire.elements.*;
import lombok.Getter;
import lombok.val;

@ExtensionPoint(location = ":ui:main")
@CssImport("./styles/aire/layout/aire-nav.css")
@CssImport("./styles/aire/layout/aire-viewport.css")
public class AireApplicationViewport extends AbstractAireContainer<Main>
    implements AireLayout, RouterLayout, HasOrderedComponents {

  /** private state */
  @Getter
  @ExtensionPoint(location = "header")
  private AireHeader header;

  @Getter
  @ExtensionPoint(location = "footer")
  private AireFooter footer;

  @ExtensionPoint(location = "content")
  private Component content;

  @ExtensionPoint(location = "navigation:primary")
  private AirePrimaryNavigation primaryNavigation;

  @ExtensionPoint(location = "navigation:secondary")
  private Component secondaryNavigation;

  private Article main;

  public AireApplicationViewport() {
    add(header = new AireHeader());
    configureStyles();
    content = new AirePanel();
    add(main = new Article());

    main.add(primaryNavigation = new AirePrimaryNavigation());
    main.add(content);

    main.add(secondaryNavigation = new AireSecondaryNavigation());
    add(footer = new AireFooter());
  }

  public void setSecondaryNavigation(Component component) {
    if (secondaryNavigation != null) {
      main.remove(secondaryNavigation);
    }
    if (component != null) {
      secondaryNavigation = component;
      val el = main.getElement();
      val size = el.getChildCount();
      el.setChild(size, component.getElement());
    }
  }

  public void addContent(Component content) {
    ((AireContainer) this.content).add(content);
  }

  public AirePrimaryNavigation getPrimaryNavigation() {
    return primaryNavigation;
  }

  public Component getSecondaryNavigation() {
    return secondaryNavigation;
  }

  public void showRouterLayoutContent(HasElement content) {
    setContent(((Component) content));
  }

  private void configureStyles() {
    getContent().setClassName("aire-viewport");
  }

  public void setContent(Component newContent) {
    ((AireContainer) content).add(newContent);
  }
}
