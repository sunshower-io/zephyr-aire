import {
  mxGraph,
  mxGraphModel
} from "mxgraph/javascript/mxClient";

export class Designer extends mxGraph {

  public id : string;

  constructor(id : string, target : HTMLElement, model? : mxGraphModel) {
    super(target, model);
    this.id = id;
  }
}