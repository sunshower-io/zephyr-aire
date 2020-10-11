package io.zephyr.aire.test.vxpath;

import com.vaadin.flow.component.HasElement;

import java.util.List;

public interface QueryExpression {

  List<HasElement> select(HasElement root);
}
