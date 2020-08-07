import {CollectionType, Property}               from '@aire/condensation/condensation';
import {ElementFactory, ElementFactoryProvider} from '@aire/designer/canvas/palette';
// import {GyreProviderFactory}                    from '<aire>/designer/gyre/provider-factory';

import {ConstraintManager, Relationship} from '@aire/designer/base/dependencies';
import {Image}                           from "@aire/designer/core";

export interface PaletteCoordinate {
  key? : string;
  namespace? : string;
  group? : string;
  name? : string;
}

export class Reference {
  static readonly type : string = 'io.sunshower.gyre.api.model.ReferenceElement';
  id : string;

  @Property({
    alias : 'target-id'
  })
  targetId : string;

  key : string;
  type : string = Reference.type;
  target : string;

  constructor() {
  }
}

export class Dependency {
  static readonly type : string =
    'io.sunshower.gyre.api.model.DependencyElement';
  required : boolean;
  identifier : string;
  relationship : Relationship;
}

export class Item {
  id : string;
  name : string;
  @Property({
    alias          : 'image',
    type           : Image,
    collectionType : CollectionType.Array
  })
  image : Image;

  @Property({
    alias : 'reference',
    type  : Reference
  })
  reference : Reference;


  @Property({
    alias          : 'dependencies',
    type           : Dependency,
    collectionType : CollectionType.Array
  })
  dependencies : Dependency[];
}

export class Palette {
  name : string;
  id : string;
  @Property({
    alias          : 'items',
    type           : Item,
    collectionType : CollectionType.Array
  })
  items : Item[];
}

export type PaletteState = 'loading' | 'loaded';

export interface PaletteEvent {
  state : PaletteState;
  factories? : ElementFactory[];
  providerFactory? : ElementFactoryProvider;
}

