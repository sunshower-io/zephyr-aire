import { Canvas, mxGraph, mxGraphHandler } from 'mxgraph';
export class GraphHandler extends mxGraphHandler {
  constructor(g: mxGraph) {
    super(g);
    this.guidesEnabled = true;
    this.setRemoveCellsFromParent(false);
  }
}
