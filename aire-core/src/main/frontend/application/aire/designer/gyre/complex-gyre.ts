import { Canvas, ElementEvent } from '@aire/designer/canvas/canvas';

import { RenderableVertex as Vertex } from '@aire/designer/model';
import { Layer } from 'mxgraph';

export class ComplexGyre extends Vertex {
  constructor(
    private name: string,
    x: number,
    y: number,
    width: number,
    height: number,
    id?: string
  ) {
    super(name, x, y, width, height, id);
  }

  onDoubleClick(sender: Canvas, event: any): void {
    sender.publishEvent({
      source: this,
      event: event,
      topic: ElementEvent.Vertices.DoubleClicked
    });
  }

  onClick(sender: any, event: any): void {}
  protected doInsert(canvas: Canvas): Layer {
    if (this.children) {
      let l = canvas.addCell(this, this.parent),
        geo = canvas.getCellGeometry(this),
        cgeo = geo.clone();
      cgeo.relative = true;
      cgeo.x = 0;
      cgeo.y = 0;
      for (let child of this.children) {
        canvas.addCell(child, this);
        canvas.getModel().setGeometry(child, cgeo);
      }
      return l;
    }
    return canvas.addCell(this, this.parent);
  }
}
