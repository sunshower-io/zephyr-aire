import { Dependency, Item } from './model';
import {
  Drawable,
  RenderableElement,
  RenderableVertex
} from 'lib/designer/model/elements';
import { Canvas } from 'lib/designer/canvas/canvas';
import { ElementFactory } from 'lib/designer/canvas/palette';

export type Relationship =
  | 'parent'
  | 'child'
  | 'predecessor'
  | 'successor'
  | 'ancestor'
  | 'descendant';

export interface Constraint {
  dependency: Dependency;
  constrained: RenderableElement;

  isSatisfied(): boolean;

  satisfy(el: RenderableElement, canvas: Canvas);

  constrain(el: RenderableElement, canvas: Canvas): void;
}

export interface ConstraintManager {
  register(id: string, factory: ElementFactory): void;

  lookup(
    dependency: Dependency,
    constrained: RenderableElement,
    canvas: Canvas
  ): Constraint;

  addUnsatisfiedConstraint(constraint: Constraint): void;

  resolveConstraint(dep: Dependency): ElementFactory;

  resolveConstraints(item: Item): ElementFactory[];
}

export class DefaultConstraintManager implements ConstraintManager {
  private unsatisfiedConstraints: Constraint[];
  private readonly tasks: Map<string, ElementFactory> = new Map<
    string,
    ElementFactory
  >();

  register(id: string, factory: ElementFactory): void {
    this.tasks.set(id, factory);
  }

  lookup(
    dependency: Dependency,
    constrained: RenderableElement,
    canvas: Canvas
  ): Constraint {
    return new ParentConstraint(this, dependency, constrained);
  }

  resolveConstraints(item: Item): ElementFactory[] {
    if (item) {
      let deps = item.dependencies,
        results = [];

      if (deps) {
        for (let dep of deps) {
          let constraint = this.resolveConstraint(dep);
          if (constraint) {
            results.push(constraint);
          }
        }
      }
      return results;
    }
    return [];
  }

  resolveConstraint(dep: Dependency): ElementFactory {
    return this.tasks.get(dep.identifier);
  }

  addUnsatisfiedConstraint(constraint: Constraint): void {
    let constraints = this.unsatisfiedConstraints || [];
    constraints.push(constraint);
    this.unsatisfiedConstraints = constraints;
  }
}

export class ParentConstraint implements Constraint {
  constructor(
    private readonly manager: ConstraintManager,
    readonly dependency: Dependency,
    public readonly constrained: RenderableElement
  ) {}

  isSatisfied(): boolean {
    let dep = this.dependency,
      constrained = this.constrained;
    return (
      dep.relationship === 'parent' &&
      !!constrained.parent &&
      dep.identifier === constrained.parent.taskId
    );
  }

  satisfy(el: RenderableElement, canvas: Canvas) {
    canvas.addCell(el, el.parent);
  }

  constrain(el: RenderableElement, canvas: Canvas) {
    canvas.addCell(el, el.parent);
    this.manager.addUnsatisfiedConstraint(this);
  }
}
