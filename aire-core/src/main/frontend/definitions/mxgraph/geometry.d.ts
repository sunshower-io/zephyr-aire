declare module "mxgraph/javascript/mxClient" {
  type Edge = mxEdge;


  export interface Layer {

    source : Layer,
    target : Layer,
    parent : Layer;
    id : string;
    value : any;
    geometry : mxGeometry;
    style : string;
    shape : mxShape;

    edges : Layer[];

    children : Layer[];


    setValue(label : string) : void;

    /**
     *
     * @param geo
     */
    setGeometry(geo : mxGeometry) : void;

    /**
     *
     * @param child
     * @param index
     */

    insert(child : Layer, index? : number) : void;

    /**
     *
     */
    isConnectable() : boolean

    /**
     *
     */
    getParent() : Layer;

    /**
     *
     * @param layer
     */
    setParent(layer : Layer) : void;

    /**
     *
     * @param edge
     */
    setEdge(edge : boolean) : void;

    /**
     *
     * @param vertex
     */
    setVertex(vertex : boolean) : void;

    /**
     *
     * @param key
     */
    getProperty(key : string) : string;

    /**
     *
     * @param key
     * @param value
     */
    setProperty(key : string, value : string) : void;

    /**
     *
     * @param style
     */

    setStyle(style : string) : void;


    /**
     *
     * @param key
     */
    getAttribute(key : string) : string;

    /**
     *
     * @param key
     * @param value
     */
    setAttribute(key : string, value : string) : void;

    /**
     *
     * @param connectable
     */
    setConnectable(connectable : boolean) : void

    /**
     *
     * @param b
     */
    setVisible(b : boolean) : void;


    /**
     *
     */
    isVertex() : boolean;

    /**
     *
     */
    isEdge() : boolean;


    /**
     *
     */
    getChildCount() : number;

    /**
     *
     * @param index
     */
    getChildAt(index : number) : Layer;

    clone() : Layer;


  }

  type Model = mxGraphModel;

  type Vertex = mxVertex;

  export interface mxConstraint {

  }

  export class mxConnectionConstraint {
    id : string;

    constructor(pt : mxPoint, a : boolean);

  }

  export class mxPolyline extends mxShape {
  }

  export class mxShape {
    id : string;
    stencil : mxShape;
    constraints : mxConstraint[];

    /**
     *
     */
    redraw() : void;

    getPorts() : Port[];

    /**
     *
     * @param state
     */
    apply(state : mxCellState) : void;

  }


  export interface Port {
    x : number;
    y : number;
    perimeter : boolean;
    constraint? : string;
  }

  export class mxPoint {
    x : number;
    y : number;

    constructor(x : number, y : number);
  }

  export class mxRectangle extends mxGeometry {


    add(rect : mxRectangle) : void;

  }

  export class mxGeometry {
    x               ? : number;
    y               ? : number;
    width           ? : number;
    height          ? : number;
    relative        ? : boolean;

    points : mxPoint[];

    constructor(
      x           ? : number,
      y           ? : number,
      width       ? : number,
      height      ? : number
    );

    clone() : mxGeometry;

    offset : mxPoint;


    translate(x : number, y : number) : void;

    getTerminalPoint(c : boolean) : mxGeometry;


  }

  export module mxGeometry {

    function fromRectangle(geometry : mxGeometry) : mxRectangle;
  }
}