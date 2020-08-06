import {mxGraph, mxGraphHandler} from 'mxgraph/javascript/mxClient';

export class GraphHandler extends mxGraphHandler {
  constructor(g : mxGraph) {
    super(g);
    this.guidesEnabled = true;
    this.setRemoveCellsFromParent(false);
  }
}
