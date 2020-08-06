/// <reference path="./index.d.ts" />
/// <reference path="./geometry.d.ts" />
/// <reference path="./styles.d.ts" />
/// <reference path="./cell.d.ts" />
/// <reference path="./handlers.d.ts" />
/// <reference path="./events.d.ts" />
declare module "mxgraph/javascript/mxClient" {



  import {mxEvent} from "mxgraph/javascript/mxClient";

  export class mxConnectionHandler {
    constructor(
      graph : mxGraph,
      connection? : (
        source : Layer,
        target : Layer,
        style : mxStylesheet
      ) => void
    );


    createEdge(
      source : Layer,
      target : Layer,
      style : mxStylesheet
    ): void;

    connect(
      source : Layer,
      target : Layer,
      event : mxEvent,
      dropTarget : Layer
    ) : void;

    createEdgeState(a : any) : mxCellState;
  }

  export module mxResources {
    export function add(resource : string) : void;
  }

  export class mxResources {

    static loadDefaultBundle : boolean;
  }

  export class mxGraphSelectionModel extends mxEventSource {

    cells : Layer[];

    /**
     *
     * @param cell
     */

    isSelected(cell:Layer) : boolean;

    removeCell(n:Layer) : void;
    addListener(t : string, fireElement : (a : mxGraphSelectionModel, b : mxEventObject) => void) : void;

    addCell(n : mxCell) : void;

  }

  export class mxGraphBounds implements Cloneable<mxGraphBounds> {
    x : number;
    y : number;
    width : number;
    height : number;

    clone() : mxGraphBounds
  }

  export class mxGraphView {

    constructor(g : mxGraph);

    x0: number;
    y0:number;
    setTranslate(x:number, y:number) : void;
    scale : number;
    currentRoot : Layer;
    translate : mxGraphBounds;

    getTranslate() : mxGraphBounds;

    addListener(l : string, m : any) : void;

    getCanvas() : Element;

    getGraphBounds() : mxRectangle;

    setGraphBounds(bounds : mxRectangle) : void;

    getBounds(cells : Layer[]) : any;

    getDrawPane() : Element;

    getBackgroundPane() : Element;

    getState(cell : Layer) : mxCellState;

    getCellStates(cells : Layer[]) : mxCellState[];

    isContainerEvent(event : Event) : boolean;

    validate() : void;
  }


  export interface MouseListener {

    mouseDown(sender : mxGraph, event : mxMouseEvent) : void;

    dragLeave(event : mxMouseEvent, state : mxCellState) : void;
  }

  type key = string | number;

  type Style = { [key : string] : any };

  export class mxControl {
    scale : number;
    bounds : mxRectangle;

  }

  export class mxCellState {
    x : number;
    y : number;
    width : number;
    height : number;


    view : any;

    cell : Layer;
    style : Style;


    shape : mxShape;
    text : mxShape;
    control : mxControl;


    constructor(view : mxGraphView, e : mxCell, style : Style);

    getPerimeterBounds(
      border ? : number,
      bounds? : mxRectangle
    ) : mxRectangle;

    getCellBounds() : mxRectangle;

  }


  export class mxMouseEvent extends mxEvent {
    getEvent() : mxEvent;

    /**
     *
     */
    getCell() : Layer;

    /**
     *
     */
    getState() : mxCellState;
  }

  export class mxPanningHandler {

  }

  export class mxCellRenderer {
    getControlBounds(
      state : mxCellState,
      w : number,
      h : number
    ) : mxRectangle;

    redrawControl(state : mxCellState, forced : boolean) : void;
  }

  export class mxEventSource {
    addListener(e: string, f:(e:any) => void) : void;

    removeListener(f:(e:any) => void) : void;
  }

  export class mxGraph implements Connectable {

    labelsVisible : boolean;

    cellsDeletable : boolean;
    autoScroll : boolean;
    gridSize : number;
    recursiveResize : boolean;
    pageVisible : boolean;

    pageFormat : mxRectangle;
    pageScale : number;
    scrollTileSize : mxRectangle;

    expandedImage : mxImage;
    collapsedImage : mxImage;


    foldingEnabled : boolean;
    isMouseDown : boolean;

    extendParents : boolean;
    extendParentsOnAdd : boolean;
    resizeContainer : boolean;
    allowDanglingEdges : boolean;
    allowLoops : boolean;
    autoExtend : boolean;

    autoTranslate: boolean;

    minimumGraphSize: mxRectangle;
    container : Element;
    view : mxGraphView;
    model : mxGraphModel;
    graphHandler : mxGraphHandler;
    cellRenderer : mxCellRenderer;
    connectionHandler : mxConnectionHandler;
    constrainChildren : boolean;
    constrainRelativeChildren : boolean;
    mouseListeners : { [name : string] : any };

    maximumGraphBounds : mxGraphBounds;

    autoSizeCell(cell : Layer, recurse : boolean) : void;

    setAutoSizeCells(b : boolean) : void;

    getChildCells(cell:Layer, bounds: boolean, recurse: boolean) : Layer[];

    getCellStyle(cell : Layer) : mxStylesheet;


    setCellStyles(key : string, value : string, cells? : mxCell[]) : void;

    createEdge(parent : mxCell, id : string, value : any, source : mxCell, target : mxCell) : mxCell;

    getPageSize() : mxRectangle;

    getPageView() : mxRectangle;
    getPageLayout() : mxRectangle;

    getPagePadding() : mxPoint;

    sizeDidChange() : void;
    scrollCellToVisible(cell:Layer, center?: boolean) : void;

    getCellOverlays(cell:Layer) : mxCellOverlay[];

    toggleCells(show:boolean, cells:Layer[], includeEdges?:boolean) : void;
    /**
     *
     * @param b
     */
    setDisconnectOnMove(b : boolean) : void;

    /**
     *
     * @param b
     */
    setAllowDanglingEdges(b : boolean) : void;

    /**
     *
     * @param cell
     */
    getCellContainmentArea(cell : Layer) : mxRectangle;

    /**
     *
     * @param extend
     */
    setExtendParents(extend : boolean) : void;

    /**
     *
     * @param cell
     */

    canImportCell(cell : Layer) : boolean;

    /**
     *
     * @param cell
     */
    isCellSelectable(cell : Layer) : boolean;

    /**
     *
     */
    zoomIn() : void;

    /**
     *
     */
    zoomOut() : void;

    /**
     *
     */
    getView() : mxGraphView;

    /**
     *
     * @param children
     */
    getBoundingBox(children : Layer[]) : mxRectangle;

    /**
     *
     * @param cell
     */
    getLabel(cell : Layer) : string | HTMLElement;


    /**
     * @param edge
     * @param source
     * @param target
     * @param index
     */
    addEdge(
      edge : Edge,
      parent : Layer,
      source : Layer,
      target : Layer,
      index      ? : number
    ) : void;

    /**
     *
     * @param changes
     */

    getSelectionCellsForChanges(changes : any) : void;

    /**
     *
     * @param back
     * @param cells
     */
    orderCells(back : boolean, cells : Layer[]) : void;


    /**
     *
     * @param cell
     */
    extendParent(cell : Layer) : void;

    /**
     *
     * @param cell
     */

    getCellGeometry(cell : mxCell) : mxGeometry;


    setPanningHandler(handler : mxPanningHandler) : void;

    /**
     *
     */

    getSelectionModel() : mxGraphSelectionModel;

    /**
     *
     * @param cell
     */

    refresh(cell? : Layer) : void;

    /**
     *
     * @param labels
     */
    setHtmlLabels(labels : boolean) : void;

    /**
     *
     * @param child
     */

    updateCellSize(child : Layer) : void;

    /**
     *
     * @param container
     * @param model
     */

    constructor(
      container? : HTMLElement,
      model? : mxGraphModel
    );


    /**
     *
     * @param cell
     * @param overlay
     */
    removeCellOverlay(
      cell : Layer,
      overlay : mxCellOverlay
    ) : mxCellOverlay;


    isRecursiveResize(state : mxCellState) : boolean;

    /**
     *
     * @param cell
     * @param dx
     * @param dy
     * @param recurse
     */
    scaleCell(
      cell : Layer,
      dx : number,
      dy : number,
      recurse : boolean
    ) : void;

    getStylesheet() : mxStylesheet;

    /**
     *
     * @param cell
     * @param bounds
     * @param recurse
     */
    resizeCell(
      cell : Layer,
      bounds : mxRectangle,
      recurse : boolean
    ) : Layer;


    /**
     *
     */
    clearSelection() : void;

    /**
     *
     * @param cells
     */
    setSelectionCells(cells : Layer[]) : void;


    /**
     *
     * @param x
     * @param y
     * @param parent
     * @param vertices
     * @param edges
     * @param ignore
     */

    getCellAt(
      x : number,
      y : number,
      parent? : Layer,
      vertices? : boolean,
      edges? : boolean,
      ignore? : (state : mxCellState, x : number, y : number) => boolean
    ) : Layer | null;

    ungroupCells(layers : Layer[]) : Layer[];

    /**
     *
     */

    createConnectionHandler() : mxConnectionHandler;

    /**
     *
     */

    isConnectable() : boolean

    /**
     *
     * @param cell
     * @param parent
     * @param index
     * @param source
     * @param target
     */

    addCell(
      cell : Layer,
      parent : Layer,
      index? : number,
      source? : Layer,
      target? : Layer
    ) : Layer ;

    /**
     *
     */

    getSelectionCells() : Layer[];

    /**
     *
     * @param cell
     */

    setSelectionCell(cell : Layer) : void;


    /**
     *
     * @param parent
     */
    getChildVertices(parent : Layer) : Layer[];

    /**
     *
     * @param cells
     * @param includeEdges
     */

    getBoundingBoxFromGeometry(
      cells : Layer[],
      includeEdges : boolean
    ) : mxRectangle;

    /**
     *
     * @param cell
     */
    getChildCells(cell : Layer) : Layer[];

    /**
     *
     * @param cells
     * @param dx
     * @param dy
     * @param disconnect
     * @param constrain
     * @param extend
     */

    cellsMoved(
      cells : Layer[],
      dx : number,
      dy : number,
      disconnect? : boolean,
      constrain? : boolean,
      extend? : boolean
    ) : void;


    /**
     *
     * @param cells
     * @param bounds
     * @param recurse
     */
    cellsResized(
      cells : Layer[],
      bounds : mxRectangle[],
      recurse : boolean
    ) : void;

    /**
     * @param group
     * @param cells
     * @param border
     */
    getBoundsForGroup(
      group : Layer,
      cells : Layer[],
      border : number
    ) : mxRectangle;


    /**
     *
     * @param cells
     */

    createGroupCell(cells : Layer[]) : Layer;

    /**
     *
     * @param event
     */
    fireEvent(event : mxEvent) : void;

    /**
     *
     * @param cells
     */

    removeCells(cells : Layer[], c? : boolean) : void;


    /**
     *
     * @param cell
     */
    getPreferredSizeForCell(cell : Layer) : mxRectangle;


    getModel() : Model;


    groupCells(
      group : Layer,
      border : number,
      cells : Layer[]
    ) : void;


    /**
     *
     */

    getDefaultParent() : Layer;

    /**
     *
     */
    getGraphBounds() : Bounds;

    /**
     *
     * @param panning
     */
    setPanning(panning : boolean) : void;

    /**
     *
     * @param cell
     */
    selectCellForEvent(cell : Layer): void;

    /**
     *
     * @param connectable
     */

    setConnectable(connectable : boolean) : void;


    /**
     *
     * @param p
     */
    removeCellsFromParent(cells : Layer[]) : void;

    /**
     *
     * @param cell
     */

    convertValueToString(cell : Layer) : string;

    /**
     *
     * @param listener
     */
    addMouseListener(listener : MouseListener) : void;


    /**
     *
     * @param vertex
     * @param overlay
     */
    addCellOverlay(
      vertex : Layer,
      overlay : mxCellOverlay
    ) : void;

    /**
     *
     * @param key
     * @param listener
     */

    addListener(
      key : string,
      listener : (sender : any, event : any) => void
    ) : boolean;

    /**
     *
     * @param key
     * @param listener
     */

    hasListener(
      key : string,
      listener : (sender : any, event : any) => void
    ) : boolean;


    /**
     *
     * @param parent
     * @param id
     * @param value
     * @param x
     * @param y
     * @param width
     * @param height
     * @param style
     */

    insertVertex(
      parent : Layer,
      id : string,
      value : any,
      x : number,
      y : number,
      width : number,
      height : number,
      style? : string
    ) : Vertex;

    /**
     *
     * @param parent
     * @param id
     * @param value
     * @param source
     * @param target
     */

    insertEdge(
      parent : Layer,
      id : string,
      value : any,
      source : Vertex,
      target : Vertex,
      style        ? : string
    ) : Edge ;

    /**
     *
     */

    createConnectionHandler() : mxConnectionHandler;

    /**
     *
     * @param cells
     * @param dx
     * @param dy
     * @param clone
     * @param parent
     * @param event
     * @param mapping
     */
    moveCells(
      cells : Layer[],
      dx : number,
      dy : number,
      clone? : boolean,
      parent? : Layer,
      event? : mxMouseEvent,
      mapping? : any
    ) : Layer[];


    /**
     *
     * @param cells
     * @param parent
     * @param index
     * @param source
     * @param target
     * @param absolute
     * @param constrain
     */

    cellsAdded(
      cells : Layer[],
      parent : Layer,
      index : number,
      source : Layer,
      target : Layer,
      absolute? : boolean,
      constrain? : boolean
    ) : void ;


    /**
     *
     * @param cells
     */
    cellsRemoved(cells : Layer[]) : void;


    /**
     *
     */
    createHandler() : mxGraphHandler;


    /**
     * @param cells
     */
    getCellsForGroup(cells : Layer[]) : Layer[];

    getConnectionPoint(vertex : Layer, constraint : mxConnectionConstraint) : mxPoint;

  }

  export class mxUndoManager {
    size : number;
    history : number;

    isEmpty() : boolean;

    clear() : void;

    undo() : void;

    canUndo() : boolean;

    redo() : boolean;

    canRedo() : boolean;

    undoableEditHappened(event : any) : void;

    addListener(a : any, b : any) : void;
  }
}