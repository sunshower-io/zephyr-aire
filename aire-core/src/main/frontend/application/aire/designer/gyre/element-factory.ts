import {
  DefaultElementFactory,
  ElementLoader
} from 'lib/designer/canvas/palette';
import { Canvas } from 'lib/designer/canvas/canvas';

import { Drawable, RenderableVertex as Vertex } from 'lib/designer/model';

import { Role } from 'lib/common/security/model/user';

import { Vertex as TaskVertex } from 'lib/designer/model/graph';
import { Dependency, Item, Reference } from 'lib/designer/base/model';
import { Images } from 'lib/utils/image';
import imgData = Images.imgData;

import { ConstraintManager } from 'lib/designer/base/dependencies';
import { Identifier } from 'aire/core/identifiers';
import { ConstructorFactory } from 'lib/designer/base/factory';
import { LoaderRegistry } from 'lib/designer/base/loader-registry';
import { createGyreLoaderRegistry } from './gyre-registry';
import { DesignerManager } from 'lib/designer/core/designer-manager';

export class GyreFactory extends DefaultElementFactory
  implements ElementLoader {
  taskName: string;
  rolesAllowed: Role[] = [new Role('admin')];
  elementName: string = 'Composite Node';

  displayIcon: string = 'assets/logos/blocks.svg';
  paletteIcon: string = 'assets/logos/blocks.svg';
  taskId: string;
  reference: Reference;

  private readonly dependencies: Dependency[];

  private readonly constructorFactory: ConstructorFactory;

  static types: LoaderRegistry;

  constructor(
    private readonly model: Item,
    public readonly constraintManager: ConstraintManager,
    private readonly designerManager: DesignerManager
  ) {
    super();
    this.taskName = model.name;
    this.taskId = model.id;
    let data = imgData(model.image);
    this.displayIcon = data;
    this.paletteIcon = data;
    this.reference = model.reference;
    this.dependencies = model.dependencies;
    constraintManager.register(model.id, this);
    if (!GyreFactory.types) {
      GyreFactory.types = createGyreLoaderRegistry(designerManager);
    }
    this.constructorFactory = GyreFactory.types.resolve(model);
  }

  newElement(
    x: number,
    y: number,
    event: Event,
    canvas: Canvas,
    target: any
  ): Drawable {
    // let l = v.layout;
    return this.constructorFactory.construct({
      loader: this,
      x: x,
      y: y,
      id: this.taskId,
      taskId: Identifier.newId(),
      isCreate: true
    });
  }

  load(model: Canvas, v: TaskVertex): Drawable {
    let l = v.layout;
    return this.constructorFactory.construct({
      loader: this,
      x: l.x,
      y: l.y,
      width: l.width,
      height: l.height,
      id: v.id,
      taskId: v.id
    });
  }

  isHostableBy(e: Drawable): boolean {
    return true;
    // if (this.dependencies) {
    //   for (let dep of this.dependencies) {
    //     if (dep.identifier == (e as any).reference.targetId) {
    //       return true;
    //     }
    //   }
    // }
    // return false;
  }

  resolveElementLoader(key: string): ElementLoader {
    return this;
  }

  handles(key: string): boolean {
    return (
      this.taskId === key || (this.reference && this.reference.targetId === key)
    );
  }
}
