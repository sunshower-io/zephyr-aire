package io.aire.core;

import com.vaadin.flow.component.Component;

public interface AireLayout<Options> extends AireComponent {

  Component get(Options o);

  boolean contains(Options o);

  void add(Options o, Component component);
}
