/// <reference path="./geometry.d.ts" />
/// <reference path="./graph.d.ts" />
/// <reference path="./cell.d.ts" />
/// <reference path="./utils.d.ts" />
/// <reference path="./constants.d.ts" />
declare module "mxgraph/javascript/mxClient" {


  export class mxXmlCanvas2D {

    scale(scale : number) : void;

    translate(x : number, y : number) : void;


  }


  export interface Cloneable<T> {
    clone() : T;
  }

  export class mxClient {
    static isBrowserSupported() : boolean;

    static basePath : string;
    static imageBasePath : string;
  }

  export class XmlDocument {

    /**
     *
     * @param key
     * @param value
     */
    setAttribute(key : string, value : string) : void;

    /**
     *
     * @param cell
     */
    appendChild(cell : XmlDocument) : void;

    /**
     *
     * @param name
     */
    createElement(name : string) : XmlDocument;

    /**
     *
     * @param namespace
     * @param name
     */

    createElementNS(namespace : string, name : string) : XmlDocument;


    /**
     *
     * @param namespace
     * @param name
     * @param xlink
     */
    setAttributeNS(namespace : string, name : string, xlink? : string) : void;

  }


  export interface Connectable {
    isConnectable() : boolean;

    setConnectable(connectable : boolean) : void;
  }


  //not technically part of mxGraph's api, but w/e.
  export interface Component<T extends Layer> {
    graph : mxGraph;
    constituent : boolean;

    cast() : T;
  }


  export class mxVertex extends mxCell implements Layer {
    children : Layer[];
    edges : Layer[];
    geometry : mxGeometry;
    id : string;
    parent : Layer;
    shape : mxShape;
    source : Layer;
    style : string;
    target : Layer;
    value : any;

    clone() : Layer;

    getAttribute(key : string) : string;

    getChildAt(index : number) : Layer;

    getChildCount() : number;

    getParent() : Layer;

    getProperty(key : string) : string;

    insert(child : Layer, index? : number) : void;

    isConnectable() : boolean;

    isEdge() : boolean;

    isVertex() : boolean;

    setAttribute(key : string, value : string) : void;

    setConnectable(connectable : boolean) : void;

    setEdge(edge : boolean) : void;

    setGeometry(geo : mxGeometry) : void;

    setParent(layer : Layer) : void;

    setProperty(key : string, value : string) : void;

    setStyle(style : string) : void;

    setValue(label : string) : void;

    setVertex(vertex : boolean) : void;

    setVisible(b : boolean) : void;


  }

  type Bounds = mxGraphBounds;


  export class mxGraphModel {

    root : Layer;


    createId() : string;


    constructor(root? : Layer);

    setValue(layer : Layer, value : any) : void;

    isCollapsed(layer : Layer) : boolean;

    addListener(k : string, v : any) : void;

    remove(cell : Layer) : Layer;

    clear() : void;


    /**
     *
     * @param layer
     */
    isEdge(layer : Layer) : boolean;

    /**
     *
     * @param layer
     */
    isVertex(layer : Layer) : boolean;

    /**
     *
     * @param cell
     * @param geometry
     */
    setGeometry(cell : Layer, geometry : mxGeometry) : void;

    /**
     *
     * @param parent
     * @param vertices
     * @param edges
     */

    getChildCells(
      parent : Layer,
      vertices : boolean,
      edges : boolean
    ) : Layer[];


    getCellState(cell : Layer) : mxCellState;

    /**
     *
     * @param parent
     * @param index
     */
    getChildAt(parent : Layer, index : number) : Layer;

    /**
     *
     * @param cell
     */
    getGeometry(cell : Layer) : mxGeometry;

    /**
     *
     */
    endUpdate() : void;

    /**
     *
     */
    beginUpdate() : void;

    getChildCount(cell : Layer) : number;

    /**
     *
     * @param id
     */
    getCell(id : string) : Layer;

    /**
     *
     * @param cell
     */
    getParent(cell : Layer) : Layer;

    /**
     *
     * @param cell
     */
    isVertex(cell : Layer) : boolean;
  }


  export class mxRubberband {
    constructor(g : mxGraph);

    static enabled : boolean;
    static defaultOpacity : number;

    currentX : number;
    currentY : number;


    mouseDown(a : any, b : any) : any;

    /**
     * @param a
     * @param b
     */

    mouseUp(a : any, b : any) : any;

    /**
     *
     * @param x
     * @param y
     */

    update(x : number, y : number) : void;

    /**
     *
     * @param a
     * @param b
     */

    mouseMove(a : any, b : any) : any;

    /**
     * @param x
     * @param y
     */
    start(x : number, y : number) : void;
  }

}
