package io.aire.core;

import com.vaadin.flow.component.Component;

public interface AireLayout<Options> extends AireComponent {

  void add(Options o, Component component);
}
