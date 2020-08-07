package io.zephyr.aire.designer;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;

@Tag("aire-designer")
@JsModule("application/aire/designer.ts")
@CssImport("./styles/aire/components/aire-designer.css")
public class AireDesigner extends Div  {


    public AireDesigner() {
        addClassName("expand");
    }

}
