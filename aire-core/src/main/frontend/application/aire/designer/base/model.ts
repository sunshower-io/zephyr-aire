import { CollectionType, Property } from 'condensation/condensation';
import { Image } from 'lib/tasks/task-model';
import { DesignerService } from './service';
import { ElementFactory, ElementFactoryProvider } from 'lib/designer/canvas/palette';
import { GyreProviderFactory } from 'lib/designer/gyre/provider-factory';
import {filter} from 'rxjs/operators'
import { Subject, Observable } from 'rxjs';

import { Property as EntityProperty } from 'lib/model/entity';
import { ConstraintManager, Relationship } from './dependencies';
import { DesignerManager } from '../core/designer-manager';

export interface PaletteCoordinate {
  key?: string;
  namespace?: string;
  group?: string;
  name?: string;
}

export class Reference {
  static readonly type: string = 'io.sunshower.gyre.api.model.ReferenceElement';
  id: string;

  @Property({
    alias: 'target-id'
  })
  targetId: string;

  key: string;
  type: string = Reference.type;
  target: string;

  constructor() {}
}

export class Dependency {
  static readonly type: string =
    'io.sunshower.gyre.api.model.DependencyElement';
  required: boolean;
  identifier: string;
  relationship: Relationship;
}

export class Item {
  id: string;
  name: string;
  @Property({
    alias: 'image',
    type: Image,
    collectionType: CollectionType.Array
  })
  image: Image;

  @Property({
    alias: 'reference',
    type: Reference
  })
  reference: Reference;

  @Property({
    alias: 'properties',
    type: EntityProperty,
    collectionType: CollectionType.Array
  })
  properties: EntityProperty[];

  @Property({
    alias: 'dependencies',
    type: Dependency,
    collectionType: CollectionType.Array
  })
  dependencies: Dependency[];
}

export class Palette {
  name: string;
  id: string;
  @Property({
    alias: 'items',
    type: Item,
    collectionType: CollectionType.Array
  })
  items: Item[];
}

export type PaletteState = 'loading' | 'loaded';

export interface PaletteEvent {
  state: PaletteState;
  factories?: ElementFactory[];
  providerFactory?: ElementFactoryProvider;
}

export class PropertySet {
  @Property({
    alias: 'properties',
    type: EntityProperty,
    collectionType: CollectionType.Array
  })
  private properties: EntityProperty[];
}

export class PaletteGroup extends Observable<PaletteState> {
  name: string;

  factory: GyreProviderFactory;
  subject: Subject<PaletteEvent>;

  constructor(
    readonly dependencyManager: ConstraintManager,
    readonly coordinate: PaletteCoordinate,
    readonly service: DesignerService,
    readonly designerManager: DesignerManager
  ) {
    super();
    this.subject = new Subject<PaletteEvent>();
  }

  listen(state: PaletteState): Observable<PaletteEvent> {
    return this.subject.pipe(filter(t => t.state == state));
  }

  async refresh(): Promise<ElementFactoryProvider> {
    this.subject.next({ state: 'loading' });
    let palettes = this.service.list(this.coordinate),
      factory = new GyreProviderFactory(
        palettes,
        this.subject,
        this.dependencyManager,
        this.designerManager
      );
    this.factory = factory;
    return factory;
  }
}
