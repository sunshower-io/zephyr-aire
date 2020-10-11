package io.zephyr.aire.test.vxpath;

import com.vaadin.flow.component.HasElement;

public interface Selector {

  enum TokenType {
    /** select a node-name. ex select("div") */
    NodeNameSelector,
    /**
     * Root selector. Select from a root ex: select("/div") selects root div(s) select("/div/a")
     * selects all children of A
     */
    RootSelector,

    /**
     * Document selector. Select from the document
     *
     * <p>ex: /a//b selects all bs who are descendents of a
     */
    DocumentSelector,
    /** select the current node */
    CurrentSelector,
    /** select the parent node */
    ParentSelector,
    /** select attributes */
    AttributeSelector
  }

  TokenType getTokenType();

  boolean matches(HasElement element);
}
