import {PolymerElement}  from "@polymer/polymer/polymer-element";
import {StampedTemplate} from "@polymer/polymer/interfaces";
import {mxGraph}         from "mxgraph/javascript/mxClient";


class AireDesigner extends PolymerElement {

  private graph : mxGraph;

  constructor() {
    super();
  }

  static get properties() {
    return {
      id : String
    };
  }


  addElement(el : any) {
    // let cell = new RenderableVertex(el, 20, 30, 200, 200, "sup");
    // cell.addTo(this.designer.getCanvas());
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
    let designer = new mxGraph(this);
    this.graph = designer;
  }


}


window.customElements.define('aire-designer', AireDesigner);

