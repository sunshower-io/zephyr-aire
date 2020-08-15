package io.zephyr.admin.ui;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.*;
import io.aire.core.AireComponent;
import io.zephyr.aire.components.controls.AireIcon;
import io.zephyr.aire.components.layouts.AireButtonGroup;
import io.zephyr.aire.components.controls.AirePill;
import io.zephyr.aire.components.AireVariant;
import io.zephyr.aire.components.layouts.AireFooter;
import io.zephyr.aire.components.layouts.AireHeader;
import io.zephyr.aire.components.layouts.AirePanel;
import io.zephyr.kernel.Dependency;
import io.zephyr.kernel.Lifecycle;
import io.zephyr.kernel.Module;
import io.zephyr.kernel.core.Kernel;
import io.zephyr.kernel.module.ModuleLifecycle;
import io.zephyr.kernel.module.ModuleLifecycleChangeGroup;
import io.zephyr.kernel.module.ModuleLifecycleChangeRequest;
import lombok.val;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModuleInfoPane extends Composite<Article>
    implements ClickNotifier<ModuleInfoPane>, AireComponent {

  private AirePill statusPill;
  private final Kernel kernel;
  private final Module module;

  public ModuleInfoPane(final Kernel kernel, final Module module) {
    this.kernel = kernel;
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
    status.add(statusPill = statusPill(module.getLifecycle().getState()));
    content.add(status);

    infoPanel.add(content);

    infoPanel.add(createControlFooter());

    return infoPanel;
  }

  private AireFooter createControlFooter() {
    val footer = new AireFooter();
    val group = new AireButtonGroup();

    val buttons = new ArrayList<Button>(3);
    val stopButton = createButton(buttons, "stop", ModuleLifecycle.Actions.Stop);
    group.add(stopButton);
    stopButton.addClassName("warning");
    val startButton = createButton(buttons, "play", ModuleLifecycle.Actions.Activate);
    group.add(startButton);
    startButton.addClassName("success");
    val removeButton = createButton(buttons, "exclamation-circle", ModuleLifecycle.Actions.Activate);
    removeButton.addClassName("error");
    group.add(removeButton);

    footer.add(group);
    return footer;
  }

  private Button createButton(
      ArrayList<Button> buttons, String iconClass, ModuleLifecycle.Actions action) {
    val result = new Button();
    buttons.add(result);
    result.setIcon(AireIcon.icon(iconClass));
    result.addClickListener(new ModuleLifecycleListener(buttons, result, action));
    return result;
  }

  class ModuleLifecycleListener implements ComponentEventListener<ClickEvent<Button>> {
    final Button button;
    final List<Button> buttons;
    final ModuleLifecycle.Actions action;

    public ModuleLifecycleListener(
        List<Button> buttons, Button button, ModuleLifecycle.Actions action) {
      this.button = button;
      this.action = action;
      this.buttons = buttons;
    }

    @Override
    public void onComponentEvent(ClickEvent<Button> buttonClickEvent) {
      val request = new ModuleLifecycleChangeRequest(module.getCoordinate(), action);
      val group = new ModuleLifecycleChangeGroup(request);
      try {
        kernel.getModuleManager().prepare(group).commit().toCompletableFuture().get();
        access(this::toggleAll);
      } catch (ExecutionException | InterruptedException ex) {

      }
    }

    private void toggleAll() {
      button.setEnabled(false);
      for (val button : buttons) {
        if (button != this.button) {
          button.setEnabled(true);
        }
      }
      val state = module.getLifecycle().getState();
      statusPill.setVariant(variantFor(state));
      statusPill.setText(state.name());
    }
  }

  private Div createContent() {
    val content = new Div();
    content.setClassName("content");
    content.getElement().setAttribute("aria-role", "document");
    return content;
  }

  private AirePill statusPill(Lifecycle.State state) {
    AireVariant variant = variantFor(state);
    return new AirePill(state.name(), variant);
  }

  private AireVariant variantFor(Lifecycle.State state) {
    switch (state) {
      case Active:
      case Starting:
        return AireVariant.Success;
      case Installed:
      case Resolved:
        return AireVariant.Info;
      case Failed:
        return AireVariant.Danger;
      default:
        return AireVariant.Warning;
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
      pill = new AirePill("service", AireVariant.Primary);
    } else {
      pill = new AirePill("library", AireVariant.Secondary);
    }
    return pill;
  }
}
