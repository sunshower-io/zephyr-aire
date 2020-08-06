import { RenderableVertex as Vertex } from 'lib/designer/model';

import { Vertex as TaskVertex } from 'lib/designer/model/graph';
import { Reference } from 'lib/designer/base/model';

import { ConstraintManager } from 'lib/designer/base/dependencies';
import { ReferenceVertex } from 'lib/designer/base/factory';

export class CompositeGyre extends Vertex implements ReferenceVertex {
  style: string = 'composite-style';

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
}
