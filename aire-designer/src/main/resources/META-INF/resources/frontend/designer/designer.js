import {
  mxGraph,
  mxRubberband,
} from "mxgraph/javascript/mxClient.js";

import {html, PolymerElement} from "@polymer/polymer/polymer-element";

class AireDesigner extends PolymerElement {

  constructor() {
    super();
  }

  static get template() {
    return html`
    <div style="width:100px; height:100px"></div>
    `;
  }

  ready() {
    super.ready();
    let graph = new mxGraph(this);
    new mxRubberband(graph);

  }

}

window.customElements.define('aire-designer', AireDesigner);

