package io.zephyr.aire.layout;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.VaadinContext;
import io.aire.core.AireContainer;
import io.aire.core.AireLayout;
import io.sunshower.aire.PaperSlider;
import io.zephyr.aire.api.ViewDecoratorManager;
import io.zephyr.aire.elements.*;

import javax.inject.Inject;

@CssImport("./styles/aire/components/layout/aire-nav.css")
@CssImport("./styles/aire/components/layout/aire-viewport.css")
public abstract class AireApplicationViewport extends AbstractAireContainer<Main>
    implements AireLayout<Region>, RouterLayout {

  /** private state */
  private AireHeader header;

  private Component footer;
  private Component content;
  private Component primaryNavigation;
  private Component secondaryNavigation;

  private Article main;

  static final String key = "aire.views.primary";

  @Inject
  public AireApplicationViewport(final ViewDecoratorManager manager) {
    manager.decorate(key, this);
    configureStyles();

    add(new PaperSlider());
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

  public void setFooter(Component content) {}

  public void setHeader(Component content) {}

  public void setPrimaryNavigation(Component primaryNavigation) {}

  public void setSecondaryNavigation(Component secondaryNavigation) {}
}
