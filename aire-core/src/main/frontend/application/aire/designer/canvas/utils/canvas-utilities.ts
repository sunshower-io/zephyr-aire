import {Canvas}                                        from "@aire/designer/canvas";
import {Layer, mxConstants, mxEvent, mxGraph, mxPoint} from "mxgraph";
import {DragSource}                                    from "@aire/designer/core/drag-source";
import {Drawable}                                      from "@aire/designer/model/elements";
import {ElementFactory}                                from "@aire/designer/canvas/palette";

type CanvasF = (mxEvent) => Canvas;
type DropTarget = Canvas | CanvasF;

export namespace CanvasUtilities {
  export function asDrawable(c : Layer) : Drawable {
    return isDrawable(c) ? (c as Drawable) : null;
  }

  export function isDrawable(c : Layer) : boolean {
    return c && (c as any).drawable;
  }

  export function makeDraggable(
    element : HTMLElement,
    graphF : DropTarget,
    factory : ElementFactory,
    dragElement : HTMLElement,
    dx? : number,
    dy? : number,
    autoscroll? : boolean,
    scalePreview? : boolean,
    highlightDropTargets? : boolean,
    getDropTarget? : (g : mxGraph, x : number, y : number, e : mxEvent) => Layer
  ) {
    let dragSource = new DragSource(graphF, element, factory);
    dragSource.dragOffset = new mxPoint(
      dx != null ? dx : 0,
      dy != null ? dy : mxConstants.TOOLTIP_VERTICAL_OFFSET
    );
    dragSource.autoscroll = autoscroll;

    dragSource.setGuidesEnabled(false);
    if (highlightDropTargets) {
      dragSource.highlightDropTargets = highlightDropTargets;
    }

    if (getDropTarget) {
      dragSource.getDropTarget = getDropTarget;
    }

    dragSource.getGraphForEvent = (evt : mxEvent) => {
      return typeof graphF == "function" ? graphF(evt) : graphF;
    };

    if (dragElement != null) {
      dragSource.createDragElement = () => {
        return dragElement.cloneNode(true);
      };

      if (scalePreview) {
        dragSource.createPreviewElement = (graph : Canvas) => {
          let elt = dragElement.cloneNode(true) as any,
            w = parseInt(elt.style.width),
            h = parseInt(elt.style.height);
          elt.style.width = Math.round(w * graph.view.scale) + "px";
          elt.style.height = Math.round(h * graph.view.scale) + "px";
          return elt;
        };
      }
    }
    return dragSource;
  }
}
