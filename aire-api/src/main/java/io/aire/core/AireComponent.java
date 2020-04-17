package io.aire.core;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.Command;

import java.util.concurrent.Future;

public interface AireComponent {

  default Future<Void> access(Command command) {
    return UI.getCurrent().access(command);
  }
}
