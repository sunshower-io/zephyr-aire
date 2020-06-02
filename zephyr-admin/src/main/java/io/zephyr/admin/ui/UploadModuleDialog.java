package io.zephyr.admin.ui;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import io.zephyr.aire.elements.AireFooter;
import io.zephyr.aire.elements.AireHeader;
import io.zephyr.aire.elements.AirePanel;
import io.zephyr.common.io.Files;
import io.zephyr.kernel.core.Kernel;
import lombok.val;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileAttribute;

public class UploadModuleDialog extends Dialog {

  private final Kernel kernel;
  private Button cancelButton;
  private Button installButton;
  private MultiFileMemoryBuffer buffer;

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
    val footer = new AireFooter();

    cancelButton = new Button("Cancel");
    cancelButton.addClickListener(this::onCancel);
    footer.add(cancelButton);

    installButton = new Button("Install");
    installButton.addClickListener(this::onSuccess);
    footer.add(installButton);
    return footer;
  }

  private void onSuccess(ClickEvent<Button> buttonClickEvent) {
    try {
      val directory = createOutputDirectory().toFile();
      for (val file : buffer.getFiles()) {
        val target = new File(directory, file);
        val dest = Files.doCheck(target.toPath());
        java.nio.file.Files.copy(
            buffer.getInputStream(file), dest, StandardCopyOption.REPLACE_EXISTING);
      }
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  private void onCancel(ClickEvent<Button> buttonClickEvent) {
    buffer = null;
    close();
  }

  private AirePanel createContent() {
    buffer = new MultiFileMemoryBuffer();
    val content = new AirePanel();

    content.add(new Upload(buffer));
    return content;
  }

  private AireHeader createHeader() {
    val header = new AireHeader();
    val h1 = new H1("Upload Plugins");
    header.add(h1);
    return header;
  }

  private Path createOutputDirectory() throws IOException {
    val dest = kernel.getFileSystem().getPath("uploaded");
    java.nio.file.Files.createDirectories(dest);
    return dest;
  }
}
