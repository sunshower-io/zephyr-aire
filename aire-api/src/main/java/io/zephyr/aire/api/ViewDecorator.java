package io.zephyr.aire.api;

import com.vaadin.flow.component.Component;

public interface ViewDecorator<T extends Component> {

  void decorate(T component);
}
