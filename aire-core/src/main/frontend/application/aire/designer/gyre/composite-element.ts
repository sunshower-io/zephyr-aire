import {RenderableVertex as Vertex} from '@aire/designer/model';

import {Reference}            from '@aire/designer/base/model';

import {ConstraintManager} from '@aire/designer/base/dependencies';
import {ReferenceVertex}   from '@aire/designer/base/factory';

export class CompositeGyre extends Vertex implements ReferenceVertex {
  style : string = 'composite-style';

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
}
