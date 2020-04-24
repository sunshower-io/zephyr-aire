package io.zephyr.admin.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import io.zephyr.aire.elements.AireFooter;
import io.zephyr.aire.elements.AireHeader;
import io.zephyr.aire.elements.AirePanel;
import io.zephyr.kernel.core.Kernel;
import lombok.val;

public class UploadModuleDialog extends Dialog {

  private final Kernel kernel;

  public UploadModuleDialog(final Kernel kernel) {
    this.kernel = kernel;

    doLayout();
  }

  private void doLayout() {
    val header = createHeader();
    val content = createContent();
    val footer = createFooter();
    add(header, content, footer);
  }

  private AireFooter createFooter() {
    return new AireFooter();
  }

  private AirePanel createContent() {
    val content = new AirePanel();
    content.add(new Button("Hello, world!"));
    return content;
  }

  private AireHeader createHeader() {
    return new AireHeader();
  }
}
