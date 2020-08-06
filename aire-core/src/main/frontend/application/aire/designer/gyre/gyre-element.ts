import {Canvas, ElementEvent} from "@aire/designer/canvas/canvas";

import {RenderableVertex as Vertex} from "@aire/designer/model";

import {Reference}         from "@aire/designer/base/model";
import {ConstraintManager} from "@aire/designer/base//dependencies";
import {ReferenceVertex}   from "@aire/designer//base/factory";

export class Gyre extends Vertex implements ReferenceVertex {
  style : string = "default-style";
  reference : Reference;

  image : string;
  manager : ConstraintManager;

  constructor(
    private name : string,
    x : number,
    y : number,
    width : number,
    height : number,
    id? : string,
    cid? : string
  ) {
    super(name, x, y, width, height, id);
    this.id = cid;
    this.taskId = id;
  }

  onDoubleClick(sender : Canvas, event : any) : void {
    sender.publishEvent({
      source : this,
      event  : event,
      topic  : ElementEvent.Vertices.DoubleClicked
    });
  }

  onClick(sender : any, event : any) : void {
  }
}
