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
      set : function (value) {
        let result = converter.convert(value);
        this["_p" + propertyKey] = result;
      },
      get : function () {
        return this["_p" + propertyKey];
      }
    });

    console.log(target);
    return property(target)(propertyKey);
  };


}