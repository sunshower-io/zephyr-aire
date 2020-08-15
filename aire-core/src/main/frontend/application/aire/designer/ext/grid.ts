import {mxGraph, mxPoint, mxGraphView, mxEvent} from 'mxgraph/javascript/mxClient';
import {Arrays}                                 from "@aire/core/array";

export interface GridOptions {
  strokeStyle? : string;

  dash? : number[];

  border? : boolean;
  gridSize? : number;
}

export const DefaultOuterGrid : GridOptions = {
  strokeStyle : "#A2A2A2",
  gridSize    : 200,
  border      : true
};

export const DefaultInnerGrid : GridOptions = {
  strokeStyle : "#BCBCBC",
  gridSize    : 40
};


export class Grid {
  private canvas : HTMLCanvasElement;


  static readonly padding : number = 20;

  constructor(
    private readonly graph : mxGraph,
    public readonly options : GridOptions
  ) {
    let canvas = document.createElement('canvas');
    graph.container.appendChild(canvas);
    this.canvas = canvas;
    this.setStyle(canvas);
    this.acceptCanvasEvents(canvas);
  }

  public hasOptions(options : GridOptions) : boolean {
    let topts = this.options;
    if (topts && !options) {
      return false;
    }
    if (options && !topts) {
      return false;
    }

    return topts.gridSize === options.gridSize
      && topts.strokeStyle === options.strokeStyle
      && Arrays.equals(topts.dash, options.dash);
  }

  public destroy() : void {
    this.canvas.remove();
  }

  public draw() : void {
    let scale = 0,
      gridSize = this.options.gridSize,
      width = 0,
      height = 0,
      translate = {x : null, y : null},
      context = this.canvas.getContext('2d');

    context.fillStyle = 'rgba(255, 255, 255, 0.5)';

    // mxGraphView.prototype.isContainerEvent = function (e : Event) {
    //   return (
    //     isContainerEvent.apply(this, arguments) ||
    //     mxEvent.getSource(e) == canvas
    //   );
    // };
    this.paintGrid(translate, scale, width, height, gridSize, context);
  }

  private setStyle(canvas : HTMLCanvasElement) {
    let padding = Grid.padding,
      paddingPx = padding + 'px';

    canvas.style.position = 'absolute';
    canvas.style.top = paddingPx;
    canvas.style.left = paddingPx;

    canvas.style.bottom = paddingPx;
    canvas.style.right = paddingPx;
    canvas.style.zIndex = '-1';
    if (this.options.border) {
      canvas.style.borderRight = '1px solid ' + this.options.strokeStyle;
      canvas.style.borderBottom = '1px solid ' + this.options.strokeStyle;
    }
  }


  private paintGrid(
    translate : mxPoint,
    scale : number,
    currentWidth : number,
    currentHeight : number,
    gridSize : number,
    context : CanvasRenderingContext2D
  ) : void {
    let graph = this.graph,
      options = this.options,
      bounds = graph.getGraphBounds(),
      padding = Grid.padding,
      width = Math.max(bounds.x + bounds.width, graph.container.clientWidth) - 2 * padding,
      height = Math.max(bounds.y + bounds.height, graph.container.clientHeight) - 2 * padding,
      sizeChanged = width != currentWidth || height != currentHeight;

    if (
      graph.view.scale != scale ||
      graph.view.translate.x != translate.x ||
      graph.view.translate.y != translate.y ||
      graph.gridSize != gridSize ||
      sizeChanged
    ) {
      scale = graph.view.scale;
      translate = graph.view.translate.clone();
      gridSize = options.gridSize;
      // currentWidth = width;
      // currentHeight = height;

      if (!sizeChanged) {
        context.clearRect(0, 0, width, height);
      } else {
        this.canvas.setAttribute('width', String(width));
        this.canvas.setAttribute('height', String(height));
      }

      let translateX = translate.x,
        translateY = translate.y,
        minStep = Math.round(gridSize / 2),
        stepping = minStep * scale;

      if (stepping < minStep) {
        let count = Math.round(Math.ceil(minStep / stepping) / 2) * 2;
        stepping = count * stepping;
      }

      let xs = Math.floor((0 - translateX) / stepping) * stepping + translateX,
        xe = Math.ceil(width / stepping) * stepping,
        ys = Math.floor((0 - translateY) / stepping) * stepping + translateY,
        ye = Math.ceil(height / stepping) * stepping;

      xe += Math.ceil(stepping);
      ye += Math.ceil(stepping);

      let ixs = Math.round(xs),
        ixe = Math.round(xe),
        iys = Math.round(ys),
        iye = Math.round(ye);

      context.strokeStyle = options.strokeStyle;

      context.beginPath();

      for (let x = xs; x <= xe + 1; x += stepping) {
        x = Math.round((x - translateX) / stepping) * stepping + translateX;
        let ix = Math.round(x);

        context.moveTo(ix + 0.5, iys + 0.5);
        context.lineTo(ix + 0.5, iye + 0.5);
      }

      for (let y = ys; y <= ye + 1; y += stepping) {
        y = Math.round((y - translateY) / stepping) * stepping + translateY;
        let iy = Math.round(y);

        context.moveTo(ixs + 0.5, iy + 0.5);
        context.lineTo(ixe + 0.5, iy + 0.5);
      }

      context.closePath();
      context.stroke();
    }
  }

  private acceptCanvasEvents(canvas : HTMLCanvasElement) {
    let pred = mxGraphView.prototype.isContainerEvent;
    mxGraphView.prototype.isContainerEvent = function (event) {
      return pred.apply(this, arguments) || mxEvent.getSource(event) === canvas;
    };

    let mxGraphViewValidateBackground = mxGraphView.prototype.validateBackground;
    let repaint = () => {
      this.draw();
    };
    mxGraphView.prototype.validateBackground = function () {
      mxGraphViewValidateBackground.apply(this, arguments);
      repaint();
    };
  }
}
