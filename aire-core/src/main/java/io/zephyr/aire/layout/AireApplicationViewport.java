package io.zephyr.aire.layout;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.router.RouterLayout;
import io.aire.core.AireContainer;
import io.aire.core.AireLayout;
import io.zephyr.aire.api.ExtensionPoint;
import io.zephyr.aire.elements.*;

@ExtensionPoint(location = ":ui:main")

@CssImport("./styles/aire/layout/aire-nav.css")
@CssImport("./styles/aire/layout/aire-button.css")
@CssImport("./styles/aire/layout/aire-viewport.css")
public abstract class AireApplicationViewport extends AbstractAireContainer<Main>
    implements AireLayout, RouterLayout {

  /** private state */
  @ExtensionPoint(location = "header")
  private AireHeader header;

  @ExtensionPoint(location = "footer")
  private Component footer;

  @ExtensionPoint(location = "content")
  private Component content;

  @ExtensionPoint(location = "navigation:primary")
  private Component primaryNavigation;

  @ExtensionPoint(location = "navigation:secondary")
  private Component secondaryNavigation;

  private Article main;

  public AireApplicationViewport() {
    add(header = new AireHeader());
    add(primaryNavigation = new AirePrimaryNavigation());
    configureStyles();
    content = new AirePanel();
    add(main = new Article());
    main.add(content);
    add(secondaryNavigation = new AireSecondaryNavigation());
    add(footer = new AireFooter());
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

  public void setFooter(Component content) {
    footer = content;
    add(content);
  }

  public void setHeader(Component content) {
    add(content);
    this.header = (AireHeader) content;
  }

  public AireHeader getHeader() {
    return header;
  }

  public void setPrimaryNavigation(Component primaryNavigation) {
    add(primaryNavigation);
  }

  public void setSecondaryNavigation(Component secondaryNavigation) {
    add(secondaryNavigation);
  }
}
