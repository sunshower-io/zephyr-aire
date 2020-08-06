import {
  DefaultElementFactory,
  ElementLoader
}               from "@aire/designer/canvas/palette";
import {Canvas} from "@aire/designer/canvas/canvas";

import {Drawable} from "@aire/designer/model";

import {Role} from "@aire/common/security/model/user";

import {Vertex as TaskVertex} from "@aire/designer/model/graph";
import {Images}               from "@aire/utils/image";
import imgData = Images.imgData;

import {ConstraintManager}  from "@aire/designer/base/dependencies";
import {Identifier}         from "@aire/core/identifiers";
import {ConstructorFactory} from "@aire/designer/base/factory";
import {Item}               from "@aire/designer/base/model";

export class GyreFactory extends DefaultElementFactory
  implements ElementLoader {
  taskName : string;
  rolesAllowed : Role[] = [new Role("admin")];
  elementName : string = "Composite Node";

  displayIcon : string = "assets/logos/blocks.svg";
  paletteIcon : string = "assets/logos/blocks.svg";
  taskId : string;


  private readonly constructorFactory : ConstructorFactory;


  constructor(
    private readonly model : Item,
    public readonly constraintManager : ConstraintManager,
  ) {
    super();
    this.taskName = model.name;
    this.taskId = model.id;
    let data = imgData(model.image);
    this.displayIcon = data;
    this.paletteIcon = data;
    constraintManager.register(model.id, this);
    // if (!GyreFactory.types) {
    //   GyreFactory.types = createGyreLoaderRegistry();
    // }
    // this.constructorFactory = GyreFactory.types.resolve(model);
  }

  newElement(
    x : number,
    y : number,
    event : Event,
    canvas : Canvas,
    target : any
  ) : Drawable {
    // let l = v.layout;
    return this.constructorFactory.construct({
      loader   : this,
      x        : x,
      y        : y,
      id       : this.taskId,
      taskId   : Identifier.newId(),
      isCreate : true
    });
  }

  load(model : Canvas, v : TaskVertex) : Drawable {
    let l = v.layout;
    return this.constructorFactory.construct({
      loader : this,
      x      : l.x,
      y      : l.y,
      width  : l.width,
      height : l.height,
      id     : v.id,
      taskId : v.id
    });
  }

  isHostableBy(e : Drawable) : boolean {
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

  resolveElementLoader(key : string) : ElementLoader {
    return this;
  }

  handles(key : string) : boolean {
    return false;
    // return (
    //   this.taskId === key || (this.reference && this.reference.targetId === key)
    // );
  }
}
