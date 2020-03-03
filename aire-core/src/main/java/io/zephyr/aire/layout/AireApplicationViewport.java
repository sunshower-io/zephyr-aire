package io.zephyr.aire.layout;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.*;
import io.aire.core.AireComponent;
import io.aire.core.AireLayout;
import io.aire.core.ComponentFactory;
import io.zephyr.aire.elements.*;

import java.util.EnumMap;

@CssImport("./styles/aire/components/layout/aire-nav.css")
@CssImport("./styles/aire/components/layout/aire-viewport.css")
public class AireApplicationViewport extends AbstractAireContainer<Main>
    implements AireLayout<AireApplicationViewport.Region> {

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

  public AireApplicationViewport() {
    this(new AireApplicationViewport.ViewportComponentFactory());
  }

  public AireApplicationViewport(ComponentFactory<Region> factory) {
    this.factory = factory;
    this.components = new EnumMap<>(Region.class);
    configureStyles();
    configureComponents(factory);
  }

  @Override
  public void add(Region region, Component component) {
    switch (region) {
      case Header:
        {
          header.add(component);
        }
    }
  }

  private void doSet(Region region, AireComponent component, HasComponents target) {
    target.add((Component) component);
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
