package io.zephyr.aire.views;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.upload.SucceededEvent;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import io.zephyr.aire.elements.AireCallToAction;
import io.zephyr.kernel.core.Kernel;
import lombok.val;

public class UploadPluginCallToAction extends AireCallToAction
    implements ComponentEventListener<SucceededEvent> {

  final Kernel kernel;

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
    val buffer = new MemoryBuffer();
    val upload = new Upload(buffer);
    upload.addSucceededListener(this);
    add(upload);
  }

  @Override
  public void onComponentEvent(SucceededEvent succeededEvent) {}
}
