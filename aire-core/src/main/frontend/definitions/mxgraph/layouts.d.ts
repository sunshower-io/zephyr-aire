declare module "mxgraph/javascript/mxClient" {


  export class mxGraphLayout implements Layout {
    execute(parent : Layer) : void;
  }

  export interface Layout {
    execute(parent : Layer) : void;
  }

  export class mxHierarchicalLayout extends mxGraphLayout implements Layout {
    resizeParent : boolean;

    constructor(graph : mxGraph, direction : string);
  }

  export class mxPartitionLayout extends mxGraphLayout implements Layout {
    constructor(graph : mxGraph, h : boolean, spacing : number, border : number);
  }

  export class mxStackLayout extends mxGraphLayout implements Layout {
    constructor(graph : mxGraph, h : boolean, spacing : number, x0 : number, y0 : number, border : number);
    constructor(graph : mxGraph, h : boolean, spacing : number);
  }

  export class mxCompactTreeLayout extends mxGraphLayout implements Layout {
    constructor(graph : mxGraph);
  }
}