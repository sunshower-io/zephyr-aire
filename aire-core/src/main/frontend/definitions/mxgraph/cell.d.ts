declare module "mxgraph/javascript/mxClient" {

  export class mxImage {
    public src : string;
    public width : number;
    public height : number;

    constructor(
      src? : string,
      width? : number,
      height? : number
    );
  }


  export class mxSelectionCellsHandler {
    mouseDown() : void;
  }

  export class mxRectangleShape {
    constructor(bounds : mxRectangle, fill : string,
                stroke : string, strokewidth : number
    );
  }


  export class mxCellOverlay {

    cursor : string;


    public image : mxImage;

    constructor(
      image? : mxImage,
      tooltip? : string,
      align? : string,
      valign? : string,
      offset? : mxPoint,
      cursor? : string
    );


    addListener(event : string, listener : (sender : any, event : mxEvent) => void) : void;
  }

  export interface Renderable {

  }

  export interface Node extends Renderable {

  }

  export interface SceneGraphElement extends Node {

  }

  export class mxCell implements Connectable, SceneGraphElement, Layer {

    source : Layer;
    target : Layer;
    parent : Layer;
    id : string;
    value : any;
    geometry : mxGeometry;
    style : string;
    shape : mxShape;
    edges : Layer[];
    children : Layer[];

    constructor(label? : string, geometry? : mxGeometry);


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

  export class mxEdge extends mxCell {

  }

}