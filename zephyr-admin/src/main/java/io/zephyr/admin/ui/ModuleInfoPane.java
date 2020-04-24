package io.zephyr.admin.ui;

import com.vaadin.flow.component.ClickNotifier;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.*;
import io.zephyr.aire.elements.AireHeader;
import io.zephyr.aire.elements.AirePanel;
import io.zephyr.aire.elements.AirePill;
import io.zephyr.kernel.Dependency;
import io.zephyr.kernel.Lifecycle;
import io.zephyr.kernel.Module;
import lombok.val;

public class ModuleInfoPane extends Composite<Article> implements ClickNotifier<ModuleInfoPane> {

  private final Module module;

  public ModuleInfoPane(final Module module) {
    this.module = module;
    doLayout();
  }

  private void doLayout() {
    getContent().add(createInfoPanel());
    getContent().add(createDependencyPanel());
  }

  private AirePanel createDependencyPanel() {
    val dependencyPanel = new AirePanel();
    val deps = module.getDependencies();
    val header = new AireHeader();
    header.add(new H1("Dependencies"));

    val content = createContent();

    if (deps.isEmpty()) {
      val noDeps = new Div();
      noDeps.addClassName("aire-centered");
      noDeps.setText("No Dependencies");
      content.add(noDeps);
    } else {
      for (val dependency : deps) {
        createDependencyContent(content, dependency);
      }
    }
    dependencyPanel.add(header);
    dependencyPanel.add(content);
    return dependencyPanel;
  }

  private AirePanel createInfoPanel() {

    val infoPanel = new AirePanel();
    val coordinate = module.getCoordinate();

    val header = new AireHeader();

    val title =
        new H1(String.format("Module: %s@%s", coordinate.getName(), coordinate.getVersion()));
    header.add(title);
    infoPanel.add(header);

    val content = createContent();
    infoPanel.add(content);

    content.add(createKvPair("Group:", coordinate.getGroup()));
    content.add(createKvPair("Name:", coordinate.getName()));
    content.add(createKvPair("Version:", coordinate.getVersion().toString()));

    val status = new Div();
    status.add(new Span("Status:"));
    status.add(statusPill(module.getLifecycle().getState()));
    content.add(status);

    infoPanel.add(content);

    return infoPanel;
  }

  private Div createContent() {
    val content = new Div();
    content.setClassName("content");
    content.getElement().setAttribute("aria-role", "document");
    return content;
  }

  private AirePill statusPill(Lifecycle.State state) {
    AirePill.Variant variant = variantFor(state);
    return new AirePill(state.name(), variant);
  }

  private AirePill.Variant variantFor(Lifecycle.State state) {
    switch (state) {
      case Active:
      case Starting:
        return AirePill.Variant.Success;
      case Installed:
      case Resolved:
        return AirePill.Variant.Info;
      case Failed:
        return AirePill.Variant.Danger;
      default:
        return AirePill.Variant.Warning;
    }
  }

  private Div createKvPair(String key, String value) {
    val div = new Div();
    val keySpan = new Span(key);
    val valSpan = new Span(value);
    div.add(keySpan, valSpan);
    return div;
  }

  private void createDependencyContent(Div content, Dependency dependency) {
    val dependencyDiv = new Div();
    val span = new Span(dependency.getCoordinate().toCanonicalForm());

    AirePill pill = createPill(dependency);

    dependencyDiv.add(span);
    dependencyDiv.add(pill);
    content.add(dependencyDiv);
  }

  private AirePill createPill(Dependency dependency) {
    val depType = dependency.getType();
    final AirePill pill;
    if (depType == Dependency.Type.Service) {
      pill = new AirePill("service", AirePill.Variant.Primary);
    } else {
      pill = new AirePill("library", AirePill.Variant.Secondary);
    }
    return pill;
  }
}
