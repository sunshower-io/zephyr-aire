import {PolymerElement}   from "@polymer/polymer/polymer-element";
import {StampedTemplate}  from "@polymer/polymer/interfaces";
import {Designer}         from "./designer/core/designer";
import {CanvasModel}      from "./designer/model/model";
import {RenderableVertex} from "./designer/model/elements";


class AireDesigner extends PolymerElement {

  constructor() {
    super();
  }


  _attachDom(dom : StampedTemplate | null) : ShadowRoot | null {
    document.body.append(dom as any);
    return null;
  }

  ready() : void {
    super.ready();
    let designer = new Designer(this, new CanvasModel());
    let cell = new RenderableVertex("hello", 20, 30, 200, 200, "sup");
    cell.addTo(designer.getCanvas());


    // let graph = new mxGraph(this) as any;
    // new mxRubberband(graph);
    // var parent = graph.getDefaultParent();
    //
    // let cell = new mxCell();

    // graph.getModel().beginUpdate();
    // try {
    //   var v1 = graph.insertVertex(parent, null, 'Hello,', 20, 20, 80, 30);
    //   var v2 = graph.insertVertex(parent, null, 'World!', 200, 150, 80, 30);
    //   graph.insertEdge(parent, null, '', v1, v2);
    // } finally {
    //   // Updates the display
    //   graph.getModel().endUpdate();
    // }


  }

}

window.customElements.define('aire-designer', AireDesigner);

