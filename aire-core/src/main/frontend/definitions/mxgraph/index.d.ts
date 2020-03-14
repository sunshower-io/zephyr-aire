declare module "mxgraph/javascript/mxClient" {
  export class mxGraph {
    constructor(el : HTMLElement);
  }


  export class mxRubberband {
    constructor(graph : mxGraph);

    getDefaultParent(): mxCell;



  }

  export class mxCell {

  }
}




