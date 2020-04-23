package io.zephyr.admin.ui;

import com.vaadin.flow.component.ClickNotifier;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import io.zephyr.aire.components.AireIconCard;
import io.zephyr.aire.elements.AirePanel;
import io.zephyr.aire.elements.ModuleResource;
import io.zephyr.kernel.Module;
import lombok.val;

public class ModuleCard extends AireIconCard implements ClickNotifier<ModuleCard> {

  final Module module;

  private final Div left;
  private final Div right;
  private final AirePanel content;

  public ModuleCard(Module module) {
    this.module = module;
    this.left = new Div();
    this.right = new Div();
    this.content = new AirePanel();
    doLayout();
    content.add(left);
    content.add(right);
    setContent(content);
  }

  private void doLayout() {
    createHeader();
    createIcon();
  }

  private void createIcon() {
    val icon = new Image();
    icon.setSrc(new ModuleResource("icon.svg", module));
    setIcon(icon);
  }

  private void createHeader() {
    val coord = module.getCoordinate();
    val header = new H1();
    header.setText(coord.getName());

    val group = new H2("group: " + coord.getGroup());
    val version = new H2("version: " + coord.getVersion());
    left.add(header, group, version);
  }
}
