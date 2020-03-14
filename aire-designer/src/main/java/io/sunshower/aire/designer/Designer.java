package io.sunshower.aire.designer;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;

@Tag("aire-designer")
@JsModule("mxgraph/javascript/mxClient.js")
@JsModule("designer/designer.js")
@NpmPackage(value = "mxgraph", version = "^4.1.0")
public class Designer extends Component {


    public Designer() {

    }

}
