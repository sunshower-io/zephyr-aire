import {
  mxGraph,
  mxClient,
  mxUndoManager,
  mxEvent,
  mxUtils,
  Layer,
  mxGraphHandler,
  mxConstants,
  mxCellState,
  mxConnectionConstraint,
  mxPoint,
  mxCell,
  mxRectangle,
  mxGraphView
} from "mxgraph/javascript/mxClient";

import {CanvasModel}                   from "@aire/designer/model/model";
import {Chord, KeyHandler}             from "@aire/designer/canvas/key-handler";
import {Action}                        from "@aire/designer/canvas/action";
import {Drawable, RenderableElement}   from "@aire/designer/model/elements";
import {ElementFactory, ElementLoader} from "@aire/designer/canvas/palette";
import {GraphHandler}                  from "@aire/designer/canvas/graph-handler";
import {DefaultConnectionHandler}      from "@aire/designer/canvas/connection-handler";
import {DefaultHoverListener}          from "@aire/designer/canvas/graph-listeners";
import {Grid}                          from "@aire/designer/core/grid";

export interface CanvasEvent<T> {
  data : T;
  type : string;
}

export interface CanvasEventListener {
  on<T>(e : CanvasEvent<T>);
}

let mxUtilsGetPortConstraints = mxUtils.getPortConstraints;
mxUtils.getPortConstraints = function (terminal, edge, source, defaultValue) {
  let key = source
            ? mxConstants.STYLE_SOURCE_PORT
            : mxConstants.STYLE_TARGET_PORT,
    id = edge.style[key],
    port = terminal.shape.getPorts()[id];

  if (port) {
    return port.constraint;
  }

  return mxUtilsGetPortConstraints.apply(this, arguments);
};

// mxConstants.GUIDE_COLOR = "#660066";
// mxConstants.HANDLE_FILLCOLOR = "#FF9900";
// mxConstants.HANDLE_STROKECOLOR = "#FF9900";
// mxConstants.VERTEX_SELECTION_COLOR = "#FF9900";
// mxConstants.DROP_TARGET_COLOR = "#4D5E7C";
// mxConstants.DEFAULT_VALID_COLOR = "#77CCA4";
// mxConstants.VALID_COLOR = "#77CCA4";
// mxConstants.OUTLINE_HIGHLIGHT_COLOR = "#2385AF";
// mxConstants.HIGHLIGHT_COLOR = "#2385AF";

mxConstants.HANDLE_SIZE = 3;
mxConstants.HANDLE_FILLCOLOR = "#6a6b8a";
mxConstants.HANDLE_STROKECOLOR = "#686b8a";
mxConstants.VERTEX_SELECTION_COLOR = "#E74694";
mxConstants.DEFAULT_VALID_COLOR = "#E74694";
mxConstants.OUTLINE_HIGHLIGHT_STROKEWIDTH = 1;
mxConstants.HIGHLIGHT_COLOR = "#E74694";
mxConstants.OUTLINE_HIGHLIGHT_COLOR = "#E74694";
mxConstants.VALID_COLOR = "#E74694";
mxConstants.EDGE_SELECTION_COLOR = "#E74694";

export namespace ElementEvent {
  export namespace Vertices {
    export const DoubleClicked : string = "vertex:double-clicked";
    export const SingleClicked : string = "vertex:single-clicked";
  }
}

export interface ElementEvent {
  topic : string;
  event : any;
  source : RenderableElement;
}

export class Canvas extends mxGraph {
  private grids : Grid[];
  private undoListener : any;

  private providers : ElementFactory[];

  public readonly keyHandler : KeyHandler;
  readonly historyManager : mxUndoManager;

  constructor(
    public readonly container : HTMLElement,
    model : CanvasModel
  ) {
    super(container, model);

    if (!mxClient.isBrowserSupported()) {
      window.alert(
        "Browser is not supported.  Please upgrade to a supported browser (Chrome, Firefox, Edge)"
      );
      throw new Error(
        "Browser is not supported.  " + "Please upgrade to a modern browser"
      );
    }
    this.foldingEnabled = false;
    // this.subject = new Subject();
    this.setConnectable(true);
    this.setAllowDanglingEdges(false);
    this.setDisconnectOnMove(false);
    this.keyHandler = this.createKeyHandler();
    this.historyManager = this.createUndoManager();
    this.addListener(mxEvent.CELLS_ADDED, this.graphChanged);
    this.addListener(mxEvent.CELLS_REMOVED, this.graphChanged);
    this.getSelectionModel().addListener(mxEvent.CHANGE, this.cellSelected);

    this.addMouseListener(new DefaultHoverListener(this));
    this.addListener(mxEvent.CLICK, this.clickListener);
    this.addListener(mxEvent.DOUBLE_CLICK, this.doubleClickListener);
    this.connectionHandler = new DefaultConnectionHandler(this);
    this.scrollTileSize = new mxRectangle(0, 0, 400, 400);

    mxEvent.addMouseWheelListener((evt, up) => {
      if (up) {
        this.zoomIn();
      } else {
        this.zoomOut();
      }
      mxEvent.consume(evt);
    });
  }

  createGraphView() : mxGraphView {
    return new mxGraphView(this);
  }

  public publishEvent(event : ElementEvent) {
    // this.eventBus.publish(event.topic, event);
  }

  protected clickListener = (sender : any, e : any) => {
    let cell = e.getProperty("cell");
    if (cell && cell.onClick) {
      let vert = cell as Drawable;
      (vert as any).onClick(sender, e, this);
    }

    // this.dispatch({
    //   data : cell,
    //   type : "cell-selected"
    // });
  };

  protected doubleClickListener = (sender : any, e : any) => {
    let cell = e.getProperty("cell");
    if (cell && cell.onDoubleClick) {
      let vert = cell as Drawable;
      (vert as any).onDoubleClick(sender, e, this);
    }

    // this.dispatch({
    //   data : cell,
    //   type : "cell-double-clicked"
    // });
  };

  private graphChanged = (sender : any, e : any) => {
    // this.dispatch({
    //   type : "graph-changed",
    //   data : this.getChildVertices(this.getDefaultParent())
    // });
  };

  private cellSelected = (sender : any, e : any) => {
    // this.dispatch({
    //   type : "selection-changed",
    //   data : this.getSelectionModel()
    // });
  };

  public getAllConnectionConstraints(terminal : mxCellState, source : boolean) {
    if (terminal && terminal.shape && terminal.shape.stencil) {
      if (terminal.shape.stencil != null) {
        return terminal.shape.stencil.constraints;
      }
    } else if (terminal && this.model.isVertex(terminal.cell)) {
      if (terminal.shape != null) {
        let ports = terminal.shape.getPorts(),
          cstrs = [];
        for (let id in ports) {
          let port = ports[id],
            cstr = new mxConnectionConstraint(
              new mxPoint(port.x, port.y),
              port.perimeter
            );
          cstr.id = id;
          cstrs.push(cstr);
        }
        return cstrs;
      }
    }

    return null;
  }

  getConnectionPoint(
    v : mxCell,
    constraint : mxConnectionConstraint
  ) : mxPoint {
    let vertex : Layer = v;
    if (constraint.id && vertex && vertex.shape) {
      const port = vertex.shape.getPorts()[constraint.id];

      if (port) {
        constraint = new mxConnectionConstraint(
          new mxPoint(port.x, port.y),
          port.perimeter
        );
      }
    }
    return super.getConnectionPoint(vertex, constraint);
  }

  public setConnectionConstraint(edge, terminal, source, constraint) : void {
    if (constraint != null) {
      let key = source
                ? mxConstants.STYLE_SOURCE_PORT
                : mxConstants.STYLE_TARGET_PORT;
      if (constraint == null || constraint.id == null) {
        this.setCellStyles(key, null, [edge]);
      } else if (constraint.id != null) {
        this.setCellStyles(key, constraint.id, [edge]);
      }
    }
  }

  public createGraphHandler() : mxGraphHandler {
    return new GraphHandler(this);
  }

  public fire(key : string) : void {
    this.keyHandler.resolve(key).run(this);
  }

  cellsMoved(
    cells : Layer[],
    dx : number,
    dy : number,
    disconnect? : boolean,
    constrain? : boolean,
    extend? : boolean
  ) : void {
    super.cellsMoved(cells, dx, dy, false, false, true);
  }

  // public listen<T>(key : string) : Observable<CanvasEvent<T>> {
  //   // return this.subject.pipe(filter(
  //   //   (v : CanvasEvent<any>, i : number) => v.type === key
  //   // ));
  // }

  // public dispatch<T>(e : CanvasEvent<T>) : void {
  //   this.eventBus.publish(ElementEvent.Vertices.SingleClicked, e);
  //   this.subject.next(e);
  // }

  public register(chord : Chord, action : Action) : void {
    this.keyHandler.bind(chord, action);
    chord.action = () => action.run(this);
  }

  public unregister(chord : Chord) : void {
    this.keyHandler.unbind(chord);
  }

  deactivate() {
    this.keyHandler.stop();
  }

  activate() : void {
  }

  isDropEnabled() : boolean {
    return true;
  }

  getDropTarget(
    cells : Layer[],
    event : mxEvent,
    cell : Layer,
    clone : boolean
  ) : Layer {
    return null;
  }

  isValidDropTarget(cell : Layer, cells : Layer[], event : mxEvent) : boolean {
    return false;
  }

  resolveElementLoader(key : string) : ElementLoader {
    for (let provider of this.providers) {
      if (provider.handles(key)) {
        return provider.resolveElementLoader(key);
      }
    }
    return null;
  }

  registerProvider(provider : ElementFactory) : void {
    if (!this.providers) {
      this.providers = [];
    }
    this.providers.push(provider);
  }

  getLabel(cell : Layer) : HTMLElement | string {
    let label = this.labelsVisible ? this.convertValueToString(cell) : "",
      geometry = this.model.getGeometry(cell);

    if (
      !this.model.isCollapsed(cell) &&
      geometry != null &&
      (geometry.offset == null ||
        (geometry.offset.x == 0 && geometry.offset.y == 0)) &&
      this.model.isVertex(cell) &&
      geometry.width >= 2
    ) {
      let style = this.getCellStyle(cell),
        fontSize =
          style[mxConstants.STYLE_FONTSIZE] || mxConstants.DEFAULT_FONTSIZE,
        max = geometry.width / (fontSize * 0.625);

      if (max < label.length) {
        return label.substring(0, max) + "...";
      }
    }

    if (cell instanceof RenderableElement) {
      let re = <RenderableElement>cell;
      if (re.labelVisible) {
        let el = document.createElement("div") as any;
        el.style.offsetTop -= 0.6 * geometry.height;
        el.classList.add(re.labelClass);
        el.innerHTML = label as string;
        return el;
      }
    }
    return label;
  }

  undo() : void {
    this.historyManager.undo();
  }

  redo() : void {
    this.historyManager.redo();
  }

  public addGrid(grid : Grid) : void {
    if (!this.grids) {
      this.grids = [grid];
    } else {
      this.grids.push(grid);
    }
    for (let grid of this.grids) {
      grid.draw();
    }
  }

  public getModel() : CanvasModel {
    return this.model;
  }

  protected createKeyHandler() : KeyHandler {
    return new KeyHandler(this);
  }

  protected createUndoManager() {
    let undoMgr = new mxUndoManager();
    this.undoListener = function (sender, evt) {
      undoMgr.undoableEditHappened(evt.getProperty("edit"));
    };

    let listener = mxUtils.bind(this, function (sender, evt) {
      this.undoListener.apply(this, arguments);
    });

    this.getModel().addListener(mxEvent.UNDO, listener);
    this.getView().addListener(mxEvent.UNDO, listener);

    let undoHandler = (sender, evt) => {
      let cand = this.getSelectionCellsForChanges(
        evt.getProperty("edit").changes
        ),
        model = this.getModel(),
        cells = [];
      for (let i = 0; i < cand.length; i++) {
        if (
          (model.isVertex(cand[i]) || model.isEdge(cand[i])) &&
          this.view.getState(cand[i]) != null
        ) {
          cells.push(cand[i]);
        }
      }
      this.setSelectionCells(cells);
      this.graphChanged(sender, evt);
    };
    undoMgr.addListener(mxEvent.UNDO, undoHandler);
    undoMgr.addListener(mxEvent.REDO, undoHandler);
    return undoMgr;
  }

  sizeDidChange() : void {
    if (this.container != null && mxUtils.hasScrollbars(this.container)) {
      let pages = this.getPageLayout(),
        pad = this.getPagePadding(),
        size = this.getPageSize(),
        minw = Math.ceil(
          (2 * pad.x) / this.view.scale + pages.width * size.width
        ),
        minh = Math.ceil(
          (2 * pad.y) / this.view.scale + pages.height * size.height
        ),
        min = this.minimumGraphSize;

      if (min == null || min.width != minw || min.height != minh) {
        this.minimumGraphSize = new mxRectangle(0, 0, minw, minh);
      }

      let dx = pad.x / this.view.scale - pages.x * size.width,
        dy = pad.y / this.view.scale - pages.y * size.height;

      if (
        !this.autoTranslate &&
        (this.view.translate.x != dx || this.view.translate.y != dy)
      ) {
        this.autoTranslate = true;
        this.view.x0 = pages.x;
        this.view.y0 = pages.y;

        let tx = this.view.translate.x,
          ty = this.view.translate.y;

        this.view.setTranslate(dx, dy);
        this.container.scrollLeft += (dx - tx) * this.view.scale;
        this.container.scrollTop += (dy - ty) * this.view.scale;

        this.autoTranslate = false;
        return;
      }
      super.sizeDidChange();
    }
  }

  getPagePadding() : mxPoint {
    return new mxPoint(
      Math.max(0, Math.round(this.container.offsetWidth - 34)),
      Math.max(0, Math.round(this.container.offsetHeight - 34))
    );
  }

  getPageSize() : mxRectangle {
    return this.pageVisible
           ? new mxRectangle(
        0,
        0,
        this.pageFormat.width * this.pageScale,
        this.pageFormat.height * this.pageScale
      )
           : this.scrollTileSize;
  }

  getPreferredPageSize(bounds, width, height) {
    let pages = this.getPageLayout(),
      size = this.getPageSize();
    return new mxRectangle(
      0,
      0,
      pages.width * size.width,
      pages.height * size.height
    );
  }

  getPageLayout() : mxRectangle {
    let size = this.pageVisible ? this.getPageSize() : this.scrollTileSize,
      bounds = this.getGraphBounds();
    if (bounds.width == 0 || bounds.height == 0) {
      return new mxRectangle(0, 0, 1, 1);
    } else {
      let x = Math.ceil(bounds.x / this.view.scale - this.view.translate.x),
        y = Math.ceil(bounds.y / this.view.scale - this.view.translate.y),
        w = Math.floor(bounds.width / this.view.scale),
        h = Math.floor(bounds.height / this.view.scale),
        x0 = Math.floor(x / size.width),
        y0 = Math.floor(y / size.height),
        w0 = Math.ceil((x + w) / size.width) - x0,
        h0 = Math.ceil((y + h) / size.height) - y0;

      return new mxRectangle(x0, y0, w0, h0);
    }
  }
}
