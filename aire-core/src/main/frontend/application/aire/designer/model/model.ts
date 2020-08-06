import {mxGraphModel} from "mxgraph/javascript/mxClient";

import {Identifier} from "../../core/identifiers";

export class CanvasModel extends mxGraphModel {
  public createId() : string {
    return Identifier.newId();
  }
}
