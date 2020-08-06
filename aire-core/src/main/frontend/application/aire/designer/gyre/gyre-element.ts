import { Canvas, ElementEvent } from 'lib/designer/canvas/canvas';

import { RenderableVertex as Vertex } from 'lib/designer/model';

import { Reference } from 'lib/designer/base/model';
import { ConstraintManager } from 'lib/designer/base//dependencies';
import { ReferenceVertex } from 'lib/designer//base/factory';

export class Gyre extends Vertex implements ReferenceVertex {
  style: string = 'default-style';
  reference: Reference;

  image: string;
  manager: ConstraintManager;

  constructor(
    private name: string,
    x: number,
    y: number,
    width: number,
    height: number,
    id?: string,
    cid?: string
  ) {
    super(name, x, y, width, height, id);
    this.id = cid;
    this.taskId = id;
  }

  onDoubleClick(sender: Canvas, event: any): void {
    sender.publishEvent({
      source: this,
      event: event,
      topic: ElementEvent.Vertices.DoubleClicked
    });
  }

  onClick(sender: any, event: any): void {}
}
