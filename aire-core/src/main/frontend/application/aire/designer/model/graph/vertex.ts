import {Layout}           from "./layout";
import {Entity, Property} from "../entity";
import {UUID}             from "../../../core/uuid";
import {Identifier}       from "../../../core/identifiers";

type ContentType = "file" | "reference";

export class PropertyAwareObject {
  type : string;
  properties? : Property[];

  constructor() {
  }
}

export class Content extends PropertyAwareObject {
  id? : string;

  reference? : string;
  value? : string;
  mediaType? : string;
  contentType? : ContentType;
  name? : string;

  static type : string = "io.sunshower.gyre.api.model.ContentElement";

  constructor(data? : any) {
    super();
    if (data) {
      Object.assign(this, data);
    }
    this.type = Content.type;
    this.id = Identifier.newId();
  }
}

export class Vertex {
  id : string;
  type : string;
  layout : Layout;

  name : string;

  entities : Entity[];

  properties : Property[];
  reference : any;

  constructor(data? : any) {
    Object.assign(this, data);
    this.properties = [];
    this.entities = [];

    if (data) {
      if (data.properties) {
      }
      if (data.entities) {
        for (let i = 0; i < data.entities.length; i++) {
          let entity = new Entity(data.entities[i]);
          this.entities.push(entity);
        }
      }
      if (!this.id) {
        this.id = UUID.random();
      }
    }
  }
}
