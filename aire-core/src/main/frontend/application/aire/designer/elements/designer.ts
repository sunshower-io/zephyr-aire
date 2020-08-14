import {PolymerElement}  from "@polymer/polymer/polymer-element";
import {StampedTemplate} from "@polymer/polymer/interfaces";
import {Designer}        from "@aire/designer/core/designer";
import Inject            from "@aire/inject/inject";
import {DesignerManager} from "@aire/designer/core/designer-manager";


class AireDesigner extends PolymerElement {

  private graph : Designer;

  @Inject
  private designerManager : DesignerManager;


  constructor() {
    super();
  }

  static get properties() {
    return {
      id : String
    };
  }

  _attachDom(dom : StampedTemplate | null) : ShadowRoot | null {
    document.body.append(dom as any);
    return null;
  }

  focus(options? : FocusOptions) : void {
    super.focus(options);
  }

  ready() : void {
    super.ready();
    console.log(this.id);
    let designer = this.designerManager.focus(this.id);
    if (!designer) {
      let designer = new Designer(this.id, this);
      this.graph = designer;
      this.designerManager.register(designer);
    } else {
      this.graph = new Designer(this.id, this, designer.getModel());
    }
  }


}


window.customElements.define('aire-designer', AireDesigner);

