import {mxGraphModel} from "mxgraph/javascript/mxClient";

import {Identifier} from "@aire/core/identifiers";

export class CanvasModel extends mxGraphModel {
  public createId() : string {
    return Identifier.newId();
  }
}


export class ModelElement {
  paletteIcon: string;
}
