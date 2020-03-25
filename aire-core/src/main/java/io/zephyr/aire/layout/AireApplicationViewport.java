package io.zephyr.aire.layout;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.router.RouterLayout;
import io.aire.core.AireContainer;
import io.aire.core.AireLayout;
import io.zephyr.aire.api.ExtensionPoint;
import io.zephyr.aire.elements.*;

@ExtensionPoint(urn = "ui:main")
@CssImport("./styles/aire/layout/aire-nav.css")
@CssImport("./styles/aire/layout/aire-viewport.css")
public abstract class AireApplicationViewport extends AbstractAireContainer<Main>
    implements AireLayout, RouterLayout {

  /** private state */
  private AireHeader header;

  @ExtensionPoint(urn = "footer", mode = ExtensionPoint.Mode.Relative)
  private Component footer;

  @ExtensionPoint(urn = "content", mode = ExtensionPoint.Mode.Relative)
  private Component content;

  @ExtensionPoint(urn = "navigation/primary", mode = ExtensionPoint.Mode.Relative)
  private Component primaryNavigation;

  @ExtensionPoint(urn = "navigation/secondary", mode = ExtensionPoint.Mode.Relative)
  private Component secondaryNavigation;

  private Article main;

  public AireApplicationViewport() {
    setHeader(new AireHeader());
    configureStyles();
    content = new AirePanel();
    add(content);
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

  public Component getHeader() {
    return header;
  }

  public void setPrimaryNavigation(Component primaryNavigation) {
    add(primaryNavigation);
  }

  public void setSecondaryNavigation(Component secondaryNavigation) {
    add(secondaryNavigation);
  }
}
