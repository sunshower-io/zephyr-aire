import {RenderableVertex as Vertex} from '../model/elements';

import {Reference} from '../base/model';

import {ConstraintManager} from '../base/dependencies';
import {ReferenceVertex}   from '../base/factory';

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
