import {Drawable} from "@aire/designer/model/elements";

import {Reference}                     from "@aire/designer/base/model";
import {ElementFactory, ElementLoader} from "@aire/designer/canvas/palette";
import {Designer}                      from "@aire/designer/core/designer";

export interface ReferenceVertex {
  reference : Reference;
  image : string;
}

export interface Descriptor {
  x? : number;
  y? : number;
  width? : number;
  height? : number;
  loader? : ElementFactory;
  id? : string;
  taskId? : string;
  isCreate? : boolean;
}

export interface ConstructorFactory {
  initialize(designer : Designer) : void;

  dispose(designer : Designer) : void;

  construct(d : Descriptor) : Drawable;
}

export abstract class AbstractConstructorFactory implements ConstructorFactory {

  dispose(manager : Designer) : void {
  }

  abstract construct(d : Descriptor) : Drawable;

  initialize(manager : Designer) : void {
    this.doRegister(manager);
  }

  protected abstract doRegister(designer : Designer) : void;
}
