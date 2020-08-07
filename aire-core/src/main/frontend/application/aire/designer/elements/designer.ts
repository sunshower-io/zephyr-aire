import {PolymerElement}   from "@polymer/polymer/polymer-element";
import {StampedTemplate}  from "@polymer/polymer/interfaces";
import {Designer}         from "@aire/designer/core/designer";
import {CanvasModel}      from "@aire/designer/model/model";
import {RenderableVertex} from "@aire/designer/model/elements";
import Inject             from "@aire/inject/inject";
import {DesignerManager}  from "@aire/designer/elements/designer-manager";


class AireDesigner extends PolymerElement {


  private designer : Designer;

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


  addElement(el : any) {
    let cell = new RenderableVertex(el, 20, 30, 200, 200, "sup");
    cell.addTo(this.designer.getCanvas());
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
    this.designer = new Designer(this, new CanvasModel());
    this.designerManager.register(this.id, this.designer);
  }

  unregister() {
    this.designerManager.unregister(this.id);
  }

}


window.customElements.define('aire-designer', AireDesigner);

