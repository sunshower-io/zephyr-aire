import {Class}    from "@aire/core/lang";
import {property} from "lit-element";

export interface Converter<T, U> {
  convert(value : T) : U;
}

export interface PropertyConversionDescriptor<T, U> {
  converter : Class<Converter<T, U>>
}

export function Property<T, U>(descriptor : PropertyConversionDescriptor<T, U>) : any {

  return function (target, propertyKey) {
    /**
     *
     */
    let converter = new descriptor.converter();
    Object.defineProperty(target, propertyKey, {
      set : (value) => {
        let result = converter.convert(value);
        target["_p" + propertyKey] = result;
      },
      get : () => {
        return target["_p" + propertyKey];
      }
    });
    return property(target)(propertyKey);
  };


}