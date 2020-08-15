package io.zephyr.aire.components.layouts;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Article;
import com.vaadin.flow.component.html.Nav;
import com.vaadin.flow.component.html.Section;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.di.Instantiator;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;
import io.aire.core.AireComponent;
import io.sunshower.gyre.CompactTrieMap;
import io.sunshower.gyre.RegexStringAnalyzer;
import io.sunshower.gyre.TrieMap;
import lombok.AllArgsConstructor;
import lombok.val;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

@CssImport("./styles/aire/components/aire-tabs.css")
public class AireTabPane extends Article
    implements RouterLayout, AireComponent, ComponentEventListener<Tabs.SelectedChangeEvent> {
  static final String CLASS_NAME = "aire-tabs";

  public enum TabPanelPlacement {
    TOP,
    BOTTOM,
    LEFT,
    RIGHT
  }

  /** immutable state */
  private final AireTabs tabs;

  private final Section contents;
  private final Nav tabContainer;
  private final TrieMap<String, Tab> locations;
  private final Map<Tab, ComponentDescriptor> components;

  /** mutable state */
  private Component current;

  private TabPanelPlacement placement;

  public AireTabPane() {
    this(TabPanelPlacement.TOP);
  }

  public AireTabPane(TabPanelPlacement placement) {
    getClassNames().addAll(Set.of(CLASS_NAME, placement.name().toLowerCase()));

    this.tabs = new AireTabs();
    decorate(tabs);
    this.tabContainer = new Nav();
    this.contents = new Section();

    tabContainer.add(tabs);
    add(tabContainer);
    add(contents);
    setTabPlacement(placement);

    this.components = new HashMap<>();
    this.locations = new CompactTrieMap<>(new RegexStringAnalyzer("/"));
  }

  public void addTab(Component o) {
    if (!(o instanceof Tab)) {
      val tab = new Tab(o);
      tabs.add(tab);
    } else {
      tabs.add((Component) o);
    }
  }

  public Tab addTab(String title, Supplier<Component> component) {
    val tab = new Tab(title);
    components.put(tab, new ComponentDescriptor(false, component, null));
    tabs.add(tab);
    return tab;
  }

  public void activate(Tab tab) {
    access(
        () -> {
          val tabComponent = components.get(tab);
          updateTab(tabComponent);
          tabs.setSelectedTab(tab);
        });
  }

  public Tab addTab(String title, Class<? extends Component> componentType) {
    val route = isRoute(componentType);
    if (route) {
      val url = getTargetUrl(componentType);
      val link = new RouterLink(title, componentType);
      val tab = new Tab(link);
      val descriptor = new ComponentDescriptor(true, null, componentType);
      components.put(tab, descriptor);
      tabs.add(tab);
      locations.put(url, tab);
      return tab;
    } else {
      val tab = new Tab(title);
      components.put(tab, new ComponentDescriptor(false, null, componentType));
      tabs.add(tab);
      return tab;
    }
  }

  public void setTabPlacement(TabPanelPlacement placement) {
    val classlist = getClassNames();
    val previousPlacement = this.placement;
    if (previousPlacement != null) {
      classlist.removeIf(t -> t.equals(previousPlacement.name().toLowerCase()));
    }
    this.placement = placement;
    classlist.add(placement.name().toLowerCase());
    updateTabOrientation(placement);
  }

  @Override
  public void onComponentEvent(Tabs.SelectedChangeEvent selectedChangeEvent) {
    val selectedTab = selectedChangeEvent.getSelectedTab();
    val next = components.get(selectedTab);
    access(() -> updateTab(next));
  }

  private void updateTab(ComponentDescriptor next) {
    if (next == null) {
      return;
    }
    // use default routing mechanism for routes
    if (!next.isRoute) {
      Component nextInstance;
      if (next.instance != null) {
        nextInstance = next.instance.get();
      } else {
        nextInstance = Instantiator.get(UI.getCurrent()).createComponent(next.componentType);
      }
      setContent(nextInstance);
    }
  }

  private void setContent(Component content) {
    if (current != null) {
      contents.remove(current);
    }
    current = content;
    contents.add(content);
  }

  public void showRouterLayoutContent(HasElement content) {
    access(() -> setContent((Component) content));
  }

  public void removeRouterLayoutContent(HasElement oldContent) {
    access(() -> contents.remove((Component) oldContent));
  }

  private void decorate(Tabs tabs) {
    tabs.addSelectedChangeListener(this);
    tabs.addAttachListener(new ActivateListener());
  }

  private boolean isRoute(Class<? extends Component> componentType) {
    return componentType.isAnnotationPresent(Route.class);
  }

  private void updateTabOrientation(TabPanelPlacement placement) {
    switch (placement) {
      case LEFT:
      case RIGHT:
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        break;
      case TOP:
      case BOTTOM:
        tabs.setOrientation(Tabs.Orientation.HORIZONTAL);
    }
  }

  private String getTargetUrl(Class<? extends Component> type) {
    val current = UI.getCurrent();
    val router = current.getRouter();
    val registry = router.getRegistry();
    val result = registry.getTargetUrl(type);
    return result.get();
  }

  private String getCurrentLocation() {
    val ui = UI.getCurrent();
    val internals = ui.getInternals();
    val viewLocation = internals.getActiveViewLocation();
    return viewLocation.getPath();
  }

  @AllArgsConstructor
  static class ComponentDescriptor {
    final boolean isRoute;
    final Supplier<Component> instance;
    final Class<? extends Component> componentType;
  }

  final class ActivateListener implements ComponentEventListener<AttachEvent> {
    @Override
    public void onComponentEvent(AttachEvent event) {
      val current = getCurrentLocation();
      val tab = locations.get(current);
      if (tab != null) {
        tabs.setSelectedTab(tab);
      }
    }
  }
}
