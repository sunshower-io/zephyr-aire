import {mxGraph, mxRubberband} from 'mxgraph/javascript/mxClient';

export class CanvasSelector extends mxRubberband {
  private minx : number;
  private miny : number;
  private maxWidth : number;
  private maxHeight : number;

  constructor(mxGraph : mxGraph, private readonly container : HTMLElement) {
    super(mxGraph);
    this.updateBounds();
  }

  private updateBounds() {
    // let c = $(this.container),
    //     pos = c.position(),
    //     width = c.width(),
    //     height = c.height();
    // this.minx = pos.left;
    // this.miny = pos.top;
    // this.maxWidth = width;
    // this.maxHeight = height;
    let c = this.container,
      width = c.offsetWidth,
      height = c.offsetHeight;
    this.minx = c.offsetLeft;
    this.miny = c.offsetTop;
    this.maxWidth = width;
    this.maxHeight = height;
  }

  update(x : number, y : number) {
    let left = this.minx,
      top = this.miny,
      width = this.maxWidth,
      height = this.maxHeight;

    if (x < left) {
      x = left;
    }
    if (x > width + left) {
      x = width + left;
    }
    if (y < top) {
      y = top;
    }
    if (y > top + height) {
      y = top + height;
    }
    super.update(x, y);
  }

  public toString() : string {
    return `Selector {
            x: ${this.minx},
            y:${this.miny},
            width:${this.maxWidth},
            height:${this.maxHeight}
        }`;
  }
}
