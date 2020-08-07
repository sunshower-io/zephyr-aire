import {Class, getClass} from "@aire/core/lang";

export type Decorator = (
  target,
  propertyKey : string,
  descriptor : PropertyDescriptor
) => void;

type Scalar = string | number;
export type Parameter =
  | Scalar
  | { [key : string] : Scalar }
  | Scalar[]
  | { [key : string] : Scalar }[];

interface DefinitionValue {
  alias? : string;
  type? : Class<any>;
  factory? : Function;
  collectionType? : CollectionType;
  nodeEditor? : Function;
  nodeWriter? : Function;
}

export enum CollectionType {
  Array,
  StringArray
}

export type PropertyDefinition = DefinitionValue | string;

export interface EntityMapping<T> {
  get(physicalName : string) : PropertyAlias;

  addPropertyAlias(physicalName : string, alias : PropertyAlias);

  resolveAlias(alias : string) : PropertyAlias;

  setFactory(physicalName : string, factory : Function);

  getFactory(physicalName : string) : Function;
}

function typeFor(propertyDef : PropertyDefinition) : Class<any> {
  let t = (propertyDef as DefinitionValue).type || Object;
  return t;
}

function aliasFor(propertyDef : PropertyDefinition) : string | null {
  if ((<DefinitionValue>propertyDef).alias) {
    return (propertyDef as DefinitionValue).alias;
  }
  if (typeof propertyDef === "string") {
    return propertyDef as string;
  }
  return null;
}

export class PropertyAlias {
  readonly alias : string;
  readonly type : Class<any>;

  constructor(
    readonly name : string,
    readonly definition : PropertyDefinition,
    readonly descriptor : PropertyDescriptor
  ) {
    this.alias = aliasFor(definition) || name;
    this.type = typeFor(definition);
  }
}

class DefaultEntityMapping<T> implements EntityMapping<T> {
  private readonly aliases : Map<string, PropertyAlias>;
  private readonly aliasesToNames : Map<string, string>;

  constructor() {
    this.aliases = new Map<string, PropertyAlias>();
    this.aliasesToNames = new Map<string, string>();
  }

  addPropertyAlias(physicalName : string, alias : PropertyAlias) {
    this.aliases.set(physicalName, alias);
    this.aliasesToNames.set(alias.alias, physicalName);
  }

  public get(physicalName : string) : PropertyAlias {
    return this.aliases.get(physicalName);
  }

  resolveAlias(alias : string) : PropertyAlias {
    let physicalName = this.aliasesToNames.get(alias);
    return physicalName && this.aliases.get(physicalName);
  }

  setFactory(physicalName : string, factory : Function) {
  }

  getFactory(physicalName : string) : Function {
    return undefined;
  }
}

export class EntityMetadata {
  readonly mappings : Map<Class<any>, EntityMapping<any>>;

  constructor() {
    this.mappings = new Map<Class<any>, EntityMapping<any>>();
  }

  size() : number {
    return this.mappings.size;
  }

  clear() : void {
    this.mappings.clear();
  }

  getAlias<T>(type : Class<T>, physicalName : string) : PropertyAlias {
    let mapping = this.mappings.get(type);
    return mapping.get(physicalName);
  }

  getMapping<T>(entityType : Class<T>) : EntityMapping<T> {
    return this.mappings.get(entityType);
  }

  register<T>(
    target : Class<T>,
    definition : PropertyDefinition,
    physicalName : string,
    descriptor : PropertyDescriptor
  ) {
    let type = getClass(target),
      existing = this.mappings.get(type) || new DefaultEntityMapping<T>();
    existing.addPropertyAlias(
      physicalName,
      new PropertyAlias(physicalName, definition, descriptor)
    );
    this.mappings.set(type, existing);
  }
}

const entityMappings = new EntityMetadata();

export function getEntityMetadata() : EntityMetadata {
  return entityMappings;
}

export function Entity(k : string) : any {
}

export function Property(definition : PropertyDefinition) : any {
  return function (
    target : Class<any>,
    key : string,
    descriptor : PropertyDescriptor
  ) : void {
    entityMappings.register(target, definition, key, descriptor);
  };
}

export function read<T, U>(type : Class<T>, v : any) : U {
  if (Array.isArray(v)) {
    return v.map(t => {
      let e = new type();
      doRead(type, e, t);
      return e;
    }) as any;
  } else {
    let entity = new type() as any;
    doRead(type, entity, v);
    return entity;
  }
}

function isPrimitive(v : any) {
  let t = typeof v;
  return (
    t === "string" || t === "number" || t === "undefined" || t === "boolean"
  );
}

function readValue(
  propertyName : string,
  v : any,
  mapping : EntityMapping<any>
) : any {
  let value = v[propertyName],
    t = typeof value,
    factory : Function,
    editor : Function = undefined;

  if (!v) {
    return null;
  }
  let entity : any = null,
    type : Class<any> = null;

  if (mapping) {
    let u = mapping.resolveAlias(propertyName);
    if (u) {
      let def = u.definition as any;
      if (def.nodeEditor) {
        editor = def.nodeEditor;
      }
      type = def && typeFor(def);
      factory = def.factory;
      if (def.collectionType === CollectionType.StringArray) {
        factory = a => String(a);
      }
    }
  } else {
    type = getClass(value);
  }

  if (editor) {
    value = editor(value);
  }
  if (factory) {
    return factory(value);
  }

  if (isPrimitive(value)) {
    return value;
  } else if (Array.isArray(value)) {
    let vs = [];
    for (let v of value) {
      let t = null;
      if (factory) {
        t = factory(v);
      } else {
        try {
          t = new type();
        } catch (e) {
          throw new Error(
            `Type "${type}" of value ${value} could not be instantiated.  Reason: ${e}`
          );
        }
      }
      if (!isPrimitive(t)) {
        doRead((t && getClass(t)) || type, t, v);
      }
      vs.push(t);
    }
    return vs;
  } else {
    if (factory) {
      entity = factory(value);
    } else {
      entity = new type();
    }
    doRead((entity && getClass(entity)) || type, entity, value);
    return entity;
  }
}

function doRead(type : Class<any>, entity : any, v : {}) {
  if (v) {
    let mapping = entityMappings.getMapping(type);

    for (let name of Object.keys(v)) {
      let propertyName = resolvePropertyName(mapping, name),
        value = readValue(name, v, mapping);
      Reflect.set(entity, propertyName, value);
    }
  } else {
    return null;
  }
}

function resolvePropertyAlias(
  mapping : EntityMapping<string>,
  name : string
) : PropertyAlias {
  if (!mapping) {
    return null;
  }
  return mapping.resolveAlias(name);
}

function resolvePropertyName(
  mapping : EntityMapping<string>,
  name : string
) : string {
  // let alias = resolvePro
  if (!mapping) {
    return name;
  }
  let physicalName = mapping.resolveAlias(name);
  let n = (physicalName && physicalName.name) || name;
  return n;
}

export function writeJson<T>(entity : T) : string {
  return JSON.stringify(write(entity));
}

export function write<T, U>(entity : T) : U {
  if (Array.isArray(entity)) {
    return entity.map(t => {
      let u = {};
      doWrite(t, u);
      return u;
    }) as any;
  } else {
    let target = {};
    doWrite(entity, target);
    return target as any;
  }
}

function doWrite(entity : any, target : any) : void {
  if (entity) {
    let mapping = entityMappings.getMapping(getClass(entity));
    for (let key of Object.keys(entity)) {
      let name = resolveName(key, mapping),
        value = resolveValue(entity, key, mapping),
        writer = resolveWriter(key, mapping);

      if (writer) {
        writer(target, value);
      } else {
        target[name] = value;
      }
    }
  }
}

function writeArray(v : any[], d : any[]) : void {
  for (let c of v) {
    if (isPrimitive(c)) {
      d.push(c);
    } else {
      let ev = {};
      doWrite(c, ev);
      d.push(ev);
    }
  }
}

function resolveValue(
  entity : any,
  propertyName : string,
  mapping : EntityMapping<any>
) : any {
  let v = entity[propertyName],
    t = typeof v;
  if (
    t === "string" ||
    t === "boolean" ||
    t === "number" ||
    t == "undefined" ||
    v instanceof Date
  ) {
    return v;
  } else {
    if (Array.isArray(v)) {
      let e = [];
      writeArray(v, e);
      return e;
    } else {
      let c = {};
      doWrite(v, c);
      return c;
    }
  }
}

function resolveWriter(
  physicalName : string,
  entityMapping : EntityMapping<any>
) : Function {
  if (entityMapping) {
    let alias = entityMapping.get(physicalName);
    if (alias && alias.definition) {
      let d = alias.definition as any;
      if (d.nodeWriter) {
        return d.nodeWriter as Function;
      }
    }
  }
  return undefined;
}

function resolveName(
  physicalName : string,
  entityMapping : EntityMapping<any>
) : string {
  if (entityMapping) {
    let alias = entityMapping.get(physicalName);
    if (alias) {
      return alias.alias;
    }
  }
  return physicalName;
}
