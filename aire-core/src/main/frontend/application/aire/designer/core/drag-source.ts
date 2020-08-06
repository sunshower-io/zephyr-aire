import { Canvas, Layer, mxDragSource, mxEvent, mxGraph } from 'mxgraph';
import { ElementFactory } from 'lib/designer/canvas/palette';
import { CanvasUtilities } from 'lib/designer/canvas/utils/canvas-utilities';
import { Drawable } from 'lib/designer/model/elements';

export class DragSource extends mxDragSource {
  constructor(
    private canvas: Canvas,
    e: HTMLElement,
    private factory: ElementFactory
  ) {
    super(e, factory.importFunction);
    this.highlightDropTargets = true;
  }

  dragEnter(c: Canvas, e: mxEvent) {
    let m = super.dragEnter(c, e);
    return m;
  }

  mouseMove(e: mxEvent): void {
    super.mouseMove(e);
  }

  drop(graph: mxGraph, e: mxEvent, t: Layer, x: number, y: number): Layer {
    return super.drop(graph, e, t, x, y);
  }

  getDropTarget(c: mxGraph, x: number, y: number, e: mxEvent): Layer {
    let p = c.getDefaultParent(),
      pgeom = p.geometry,
      px = (pgeom && pgeom.x) || 0,
      py = (pgeom && pgeom.y) || 0,
      d = Number.MAX_SAFE_INTEGER,
      [, val] = this.findDropTarget(p, x, y, d, px, py);

    if (val) {
      this.factory.setDropTarget(val as Drawable);
    }
    return val;
  }

  //if this gets slow we can implement KD or BS partitioning.
  private findDropTarget(
    p: Layer,
    x: number,
    y: number,
    m: number,
    pox: number,
    poy: number
  ): [number, Layer] {
    let min = null,
      rn = m;
    if (p && p.children && p.children.length) {
      for (let c of p.children) {
        let drawable = CanvasUtilities.asDrawable(c);
        if (drawable && this.factory.isHostableBy(drawable)) {
          let geo = drawable.geometry,
            gx = geo.x + pox,
            gy = geo.y + poy,
            dx = Math.sqrt(Math.pow(x - gx, 2) + Math.pow(y - gy, 2));
          if (dx < rn) {
            rn = dx;
            min = drawable;
          }
        }
        let pgeo = c.geometry,
          [cd, cval] = this.findDropTarget(
            c,
            x,
            y,
            m,
            pgeo.x + pox,
            pgeo.y + poy
          );
        if (cd < rn) {
          rn = cd;
          min = cval;
        }
      }
    }
    return [rn, min];
  }
}
