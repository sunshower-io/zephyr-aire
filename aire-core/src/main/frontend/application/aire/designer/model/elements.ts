import {
  Layer,
  mxCell,
  mxCellOverlay,
  mxEvent,
  mxGeometry,
  mxRectangleShape,
  mxShape,
  Port
} from 'mxgraph/javascript/mxClient';

import {Canvas}            from "../canvas/canvas";
import {Identifier}        from "../../core/identifiers";
import {Class}             from "../../core/lang";
import {Dependency}        from '../base/model';
import {ConstraintManager} from '../base/dependencies';

export class Overlay extends mxCellOverlay {
}

export interface Drawable extends Layer {
  taskId : string;

  drawable : boolean;
  labelVisible : boolean;
  labelClass : string;
  dropTarget : boolean;

  getLabel() : string;

  getOverlays() : Overlay[];

  addTo(canvas : Canvas) : boolean;

  listensFor(event : mxEvent) : boolean;

  addChild(v : Drawable) : void;
}

export let defaultPorts : Port[] = [
  {x : 0, y : 0.5, perimeter : true},
  {x : 1, y : 0.5, perimeter : true},
  {x : 0.5, y : 0, perimeter : true},
  {x : 0.5, y : 1, perimeter : true}
];

export function decorateShape(shape : Class<mxRectangleShape>, ports : Port[]) {
  shape.prototype.getPorts = () => {
    return ports;
  };
}

decorateShape(mxRectangleShape, defaultPorts);

export abstract class RenderableElement extends mxCell implements Drawable {
  id : string;
  taskId : string;
  drawable : boolean;
  dropTarget : boolean;

  labelVisible : boolean = true;
  labelClass : string = 'default-label';
  cellOverlays : Overlay[];

  shape : mxShape;
  parent : Drawable;
  children : Layer[];
  dependencies ? : Dependency[];
  manager ? : ConstraintManager;

  constructor(
    label : string,
    x : number,
    y : number,
    width : number,
    height : number
  ) {
    super(label, new mxGeometry(x, y, width, height));
    this.drawable = true;
    this.dropTarget = false;
  }

  listensFor(event : mxEvent) : boolean {
    return false;
  }

  getLabel() : string {
    return Identifier.newId();
  }

  addOverlay(overlay : Overlay) {
    this.cellOverlays = this.cellOverlays || [];
    this.cellOverlays.push(overlay);
  }

  getOverlays() : Overlay[] {
    return this.cellOverlays;
  }

  addTo(canvas : Canvas) : boolean {
    canvas.getModel().beginUpdate();
    try {
      this.doInsert(canvas);
    } finally {
      canvas.getModel().endUpdate();
    }
    return true;
  }

  /**
   * TODO: refactor
   */

  protected doInsert(canvas : Canvas) : Layer {
    if (this.dependencies && this.dependencies.length) {
      let mgr = this.manager;
      for (let dep of this.dependencies) {
        let constraint = mgr.lookup(dep, this, canvas);
        if (constraint.isSatisfied()) {
          constraint.satisfy(this, canvas);
          if (this.cellOverlays) {
            for (let overlay of this.cellOverlays) {
              canvas.addCellOverlay(this, overlay);
            }
          }
        } else {
          constraint.constrain(this, canvas);
          for (let overlay of this.cellOverlays) {
            canvas.addCellOverlay(this, overlay);
          }
        }
      }
    } else {
      let l = canvas.addCell(this, this.parent);
      if (this.cellOverlays) {
        for (let overlay of this.cellOverlays) {
          canvas.addCellOverlay(this, overlay);
        }
      }
      return l;
    }
  }

  addChild(v : RenderableElement) : void {
    // v.setParent(this);
    if (!this.children) {
      this.children = [];
    }
    this.children.push(v);
  }
}

export abstract class Edge extends mxCell {
  id : string;

  constructor(
    public source : RenderableVertex,
    public target : RenderableVertex
  ) {
    super();
    this.id = Identifier.newId();
  }
}

export class RenderableVertex extends RenderableElement {
  container : boolean;

  constructor(
    label : string,
    x : number,
    y : number,
    width : number,
    height : number,
    id? : string
  ) {
    super(label, x, y, width, height);
    this.setVertex(true);
    this.id = id;
  }
}


