package io.zephyr.aire.layout;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.router.RouterLayout;
import io.aire.core.AireComponent;
import io.aire.core.AireContainer;
import io.aire.core.AireLayout;
import io.aire.core.ComponentFactory;
import io.zephyr.aire.api.ViewDecoratorManager;
import io.zephyr.aire.elements.*;

import javax.inject.Inject;
import java.util.EnumMap;
import java.util.NoSuchElementException;

@CssImport("./styles/aire/components/layout/aire-nav.css")
@CssImport("./styles/aire/components/layout/aire-viewport.css")
public class AireApplicationViewport extends AbstractAireContainer<Main>
    implements AireLayout<AireApplicationViewport.Region>, RouterLayout {

  /** public state */
  public enum Region {
    Footer,
    Header,
    Content,
    PrimaryNavigation,
    SecondaryNavigation,
  }

  /** private state */
  private AireHeader header;

  private Component footer;
  private Component content;
  private Component primaryNavigation;
  private Component secondaryNavigation;

  private Article main;

  private final ComponentFactory<Region> factory;

  private final EnumMap<Region, Component> components;
  static final String key = "aire.views.primary";

  @Inject
  public AireApplicationViewport(final ViewDecoratorManager manager) {
    this(new AireApplicationViewport.ViewportComponentFactory());
    manager.decorate(key, this);
  }

  public AireApplicationViewport(ComponentFactory<Region> factory) {
    this.factory = factory;
    this.components = new EnumMap<>(Region.class);
    configureStyles();
    configureComponents(factory);
  }

  @Override
  public Component get(Region o) {
    if (!contains(o)) {
      throw new NoSuchElementException("No component configured in slot <" + o + ">");
    }
    return components.get(o);
  }

  @Override
  public boolean contains(Region o) {
    return components.containsKey(o);
  }

  @Override
  public void add(Region region, Component component) {
    ((HasComponents) get(region)).add(component);
  }

  public void showRouterLayoutContent(HasElement content) {
    setContent(((Component) content));
  }

  public void setContent(Component newContent) {
    ((AireContainer) content).add(newContent);
  }

  private void configureStyles() {
    getContent().setClassName("aire-viewport");
  }

  private void configureComponents(ComponentFactory<Region> factory) {
    header = (AireHeader) factory.create(Region.Header);
    footer = (Component) factory.create(Region.Footer);
    content = (AirePanel) factory.create(Region.Content);
    primaryNavigation = (Component) factory.create(Region.PrimaryNavigation);
    secondaryNavigation = (Component) factory.create(Region.SecondaryNavigation);

    main = new Article();
    main.add(primaryNavigation, content, secondaryNavigation);
    getContent().add(header, main, footer);

    components.put(Region.Header, header);
    components.put(Region.Footer, footer);
    components.put(Region.PrimaryNavigation, primaryNavigation);
    components.put(Region.SecondaryNavigation, secondaryNavigation);
    components.put(Region.Content, content);
  }

  static final class ViewportComponentFactory implements ComponentFactory<Region> {

    @Override
    public AireComponent create(Region region) {
      switch (region) {
        case Header:
          return new AireHeader();
        case Footer:
          return new AireFooter();
        case Content:
          return new AirePanel();
        case PrimaryNavigation:
          return new AirePrimaryNavigation();
        case SecondaryNavigation:
          return new AireSecondaryNavigation();
      }
      throw new IllegalArgumentException("Region was comprehensive");
    }
  }
}
