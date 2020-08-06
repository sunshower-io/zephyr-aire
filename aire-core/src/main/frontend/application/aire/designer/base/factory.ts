import { Drawable } from 'lib/designer/model';

import { Reference } from './model';
import { ElementFactory, ElementLoader } from 'lib/designer/canvas/palette';
import { Subscription } from 'aurelia-event-aggregator';
import { Designer } from 'lib/designer/core/designer';

export interface ReferenceVertex {
  reference: Reference;
  image: string;
}

export interface Descriptor {
  x?: number;
  y?: number;
  width?: number;
  height?: number;
  loader?: ElementFactory;
  id?: string;
  taskId?: string;
  isCreate?: boolean;
}

export interface ConstructorFactory {
  initialize(designer: Designer): void;
  dispose(designer: Designer): void;
  construct(d: Descriptor): Drawable;
}

export abstract class AbstractConstructorFactory implements ConstructorFactory {
  private subscription: Subscription;

  dispose(manager: Designer): void {}

  abstract construct(d: Descriptor): Drawable;

  initialize(manager: Designer): void {
    this.doRegister(manager);
  }

  protected abstract doRegister(designer: Designer): void;
}
