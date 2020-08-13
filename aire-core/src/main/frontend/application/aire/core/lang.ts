export function Enum<T extends string>(o: Array<T>): { [K in T]: K } {
  return o.reduce((res, key) => {
    res[key] = key;
    return res;
  }, Object.create(null));
}


export module URLS {
  export const PATTERN = /((([A-Za-z]{3,9}:(?:\/\/)?)(?:[-;:&=\+\$,\w]+@)?[A-Za-z0-9.-]+|(?:www.|[-;:&=\+\$,\w]+@)[A-Za-z0-9.-]+)((?:\/[\+~%\/.\w-_]*)?\??(?:[-\+=&;%@.\w_]*)#?(?:[\w]*))?)/;


  export function params(params: any) : string {
    return "";
  }
}

export function Mixin(baseCtors: Function[]) {
  return function(derivedCtor: Function) {
    baseCtors.forEach(baseCtor => {
      Object.getOwnPropertyNames(baseCtor.prototype).forEach(name => {
        derivedCtor.prototype[name] = baseCtor.prototype[name];
      });
    });
  };
}

export const range = (start, end) =>
  Array.from({ length: end - start }, (v, k) => k + start);

export type Class<T> = { new (...args: any[]): T };

export function getClass<T>(t: T): Class<T> {
  let a = t.constructor;
  return <Class<T>>a;
}

const falsy = /^(?:f(?:alse)?|no?|0+)$/i;
export const parseBoolean: (o: any) => boolean = val => {
  return !falsy.test(val) && !!val;
};

export function isFalse(a: any) {
  return !parseBoolean(a);
}

export interface Serializable {
  toJson(): string;
}

export namespace Types {
  export const Identifier = 'io.sunshower.sdk.lang.IdentifierElement';
}

export interface Identifier {
  type: string;
  value: string;
}

export interface LoadingListener {
  onLoadingStart();

  onLoadingFinished();

  onLoadError(e);
}
