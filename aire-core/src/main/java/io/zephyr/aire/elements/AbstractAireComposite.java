package io.zephyr.aire.elements;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import io.aire.core.AireComposite;

public class AbstractAireComposite<T extends Component> extends Composite<T>
    implements AireComposite {}
