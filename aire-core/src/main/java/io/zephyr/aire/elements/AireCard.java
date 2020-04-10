package io.zephyr.aire.elements;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Section;

@CssImport("./styles/aire/components/aire-card.css")
public class AireCard extends Composite<Div> {

  private Div icon;
  private Section content;
  private AireHeader header;
  private AireFooter footer;

  public AireCard() {}

  public void setContent(Component content) {}
}
