import { Property } from 'condensation/condensation';

export class PropertyMetadata {
  static readonly type: string =
    'io.sunshower.gyre.api.model.PropertyMetadataElement';

  @Property('property-type')
  propertyType: string;

  type: string = PropertyMetadata.type;
}

export class TaskMetadata {
  id: string;

  @Property({
    alias: 'properties',
    type: PropertyMetadata
  })
  private properties: PropertyMetadata[];

  static readonly type: string = 'io.sunshower.gyre.api.model.MetadataElement';
  name: string;
}
