export class Entity {
  id ? : string;
  name ? : string;
  properties ? : Property[];

  constructor(data? : any) {
    this.properties = [];
    if (data) {
      this.name = data.name;
    }
  }
}

type ContentType = 'text' | 'number' | 'boolean' | 'password';

export class Property {
  id? : string;
  label? : string;
  type? : ContentType;
  value? : string;
  isPrivate? : boolean;

  constructor(data? : any) {
    if (data) {
      if (data.id) {
        this.id = data.id;
      }
      if (data.key) {
        this.label = data.key;
      }
      if (data.value) {
        this.value = data.value;
      }

      let propertyType = data['property-type'];
      if (
        propertyType &&
        data['property-type'] ==
        'io.sunshower.service.model.properties.StringProperty'
      ) {
        this.type = 'text';
      }
      if (
        propertyType &&
        data['property-type'] ==
        'io.sunshower.service.model.properties.IntegerProperty'
      ) {
        this.type = 'number';
      }
      if (
        propertyType &&
        data['property-type'] ==
        'io.sunshower.service.model.properties.BooleanProperty'
      ) {
        this.type = 'boolean';
      }
      if (
        propertyType &&
        data['property-type'] ==
        'io.sunshower.service.model.properties.SecretProperty'
      ) {
        this.type = 'password';
      }
    }
  }
}
