package io.sunshower.aire;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;

@Tag("aire-card")
@JsModule("@sunshower-aire/aire-card/aire-card.js")
//@NpmPackage(value = "@sunshower-aire/aire-card", version = "^1.0.0")
/*
 If you wish to include your own JS modules in the add-on jar, add the module
 files to './src/main/resources/META-INF/resources/frontend' and insert an
 annotation @JsModule("./my-module.js") here.
*/
public class AireCard extends Component {

    public AireCard() {
    }

}
