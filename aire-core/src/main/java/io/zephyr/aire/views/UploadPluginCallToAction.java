package io.zephyr.aire.views;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.upload.SucceededEvent;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import io.zephyr.aire.components.layouts.AireCallToAction;
import io.zephyr.common.io.Files;
import io.zephyr.kernel.Coordinate;
import io.zephyr.kernel.Module;
import io.zephyr.kernel.core.Kernel;
import io.zephyr.kernel.module.*;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import static java.nio.file.Files.copy;

@Slf4j
public class UploadPluginCallToAction extends AireCallToAction
    implements ComponentEventListener<SucceededEvent> {

  final Kernel kernel;
  private MultiFileMemoryBuffer buffer;

  public UploadPluginCallToAction(Kernel kernel) {
    super();
    layout();
    this.kernel = kernel;
  }

  private void layout() {

    val container = new Div();
    val h1 = new H1("This instance has no plugins installed");
    val h2 = new H2("Drop or upload one or more plugin files to continue");
    container.add(h1, h2);
    add(container);
    layoutUpload();
  }

  private void layoutUpload() {
    buffer = new MultiFileMemoryBuffer();
    val upload = new Upload(buffer);
    upload.addSucceededListener(this);
    add(upload);

    val button = new Button("Install");
    button.addClickListener(this::onSuccess);
    add(button);
  }

  private Path createOutputDirectory() throws IOException {
    val dest = kernel.getFileSystem().getPath("uploaded");
    java.nio.file.Files.createDirectories(dest);
    return dest;
  }

  private void onSuccess(ClickEvent<Button> event) {
    try {
      val uploaded = new HashSet<Path>();
      val directory = createOutputDirectory().toFile();
      copyFiles(uploaded, directory);
      installFiles(uploaded);
    } catch (IOException ex) {
      log.warn("Failed to install module.  Reason: {} ", ex.getMessage());
      log.debug("Full trace: ", ex);
    } finally {
      //      buffer = null;
    }
  }

  private void installFiles(Set<Path> uploaded) {
    val existingModules =
        kernel.getModuleManager().getModules().stream()
            .map(Module::getCoordinate)
            .collect(Collectors.toSet());

    val group = new ModuleInstallationGroup();
    try {
      for (val upload : uploaded) {
        val request = new ModuleInstallationRequest();
        request.setLocation(upload.toUri().toURL());
        group.add(request);
      }

      kernel.getModuleManager().prepare(group).commit().toCompletableFuture().get();

      startAll(existingModules);

    } catch (MalformedURLException | InterruptedException | ExecutionException ex) {
      log.warn("Somehow a local filesystem URL was malformed.  Reason at debug");
      log.debug("Reason: ", ex);
    }
  }

  private void startAll(Set<Coordinate> existingModules)
      throws ExecutionException, InterruptedException {
    val group = new ModuleLifecycleChangeGroup();
    for (val module : kernel.getModuleManager().getModules()) {
      if (!existingModules.contains(module.getCoordinate())) {
        val request =
            new ModuleLifecycleChangeRequest(
                module.getCoordinate(), ModuleLifecycle.Actions.Activate);
        group.addRequest(request);
      }
    }
    kernel.getModuleManager().prepare(group).commit().toCompletableFuture().get();
  }

  private void copyFiles(Set<Path> uploaded, File directory) throws IOException {
    for (val file : buffer.getFiles()) {
      val target = new File(directory, file);
      val dest = Files.doCheck(target.toPath());
      uploaded.add(dest);
      copy(buffer.getInputStream(file), dest, StandardCopyOption.REPLACE_EXISTING);
    }
  }

  @Override
  public void onComponentEvent(SucceededEvent succeededEvent) {}
}
