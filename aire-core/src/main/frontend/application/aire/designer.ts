import {
  mxGraph,
  mxRubberband
}                        from "mxgraph/javascript/mxClient";
import {PolymerElement}  from "@polymer/polymer/polymer-element";
import {StampedTemplate} from "@polymer/polymer/interfaces";


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
    let graph = new mxGraph(this) as any;
    new mxRubberband(graph);
    var parent = graph.getDefaultParent();

    graph.getModel().beginUpdate();
    try {
      var v1 = graph.insertVertex(parent, null, 'Hello,', 20, 20, 80, 30);
      var v2 = graph.insertVertex(parent, null, 'World!', 200, 150, 80, 30);
      graph.insertEdge(parent, null, '', v1, v2);
    } finally {
      // Updates the display
      graph.getModel().endUpdate();
    }


  }

}

window.customElements.define('aire-designer', AireDesigner);

