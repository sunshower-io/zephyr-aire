import {UUID}      from "./uuid";
import {Predicate} from "./predicates";


export class dom {


}


export namespace dom {

  export const $$ : dom = new dom();

  export function $(s : string) : Element {
    return document.querySelector(s);
  }

  export module siblings {

    export function reducePrevious<T>(el : Element, i : T, f : (sib : Element, aggregate : T) => T) : T {
      let t : T = i;
      for (let psib = el.previousElementSibling; psib; psib = psib.previousElementSibling) {
        t = f(psib, t);
      }
      return t;
    }

    export function reduceNext<T>(el : Element, i : T, f : (sib : Element, aggregate : T) => T) : T {
      let t : T = i;
      for (let nsib = el.nextElementSibling; nsib; nsib = nsib.nextElementSibling) {
        t = f(nsib, t);
      }
      return t;
    }

    export function previous(el : Element) : Element[] {
      return reducePrevious(el, [], (e : Element, t : Element[]) => {
        t.push(e);
        return t;
      });
    }

    export function next(el : Element) : Element[] {
      return reduceNext(el, [], (e : Element, t : Element[]) => {
        t.push(e);
        return t;
      });
    }
  }


  export function isAncestor(test : Element, target : Element) {
    if (test === target) {
      return true;
    }
    let c = test;
    while ((c = c.parentElement) && c !== target) {
    }
    return !!c;
  }


  export function applyIf(el : Element) : Predicate {
    return new Predicate(el);
  }

  export function decorateTo(
    sourceEl : Element,
    el : Element,
    decoration : string,
    className? : string
  ) {
    if (sourceEl.hasAttribute(decoration)) {
      if (className) {
        el.classList.add(className);
      } else {
        el.classList.add(decoration);
      }
    }
  }

  export function decorate(
    el : Element,
    decoration : string,
    className? : string
  ) : boolean {
    if (el.hasAttribute(decoration)) {
      if (className) {
        el.classList.add(className);
      } else {
        el.classList.add(decoration);
      }
      return true;
    }
    return false;
  }
}

export declare type Class = Function;

export function generated(instance : any, key : string) {
  let value = instance[key] || UUID.random(),
    getter = function () : string {
      return value;
    },
    setter = function (v : string) : void {
      value = v;
    };
  if (delete instance[key]) {
    Object.defineProperty(instance, key, {
      get          : getter,
      set          : setter,
      enumerable   : true,
      configurable : true
    });
  }
}

export function findParentByClass(el : Element, selectorClass : string) : Element {
  if (Element.prototype.closest) {
    return el.closest(selectorClass);
  }
  while (
    (el = el.parentElement) &&
    !(el.matches || (el as any).matchesSelector).call(el, selectorClass)
    ) {
  }
  return el;
}

export function createEvent(name : string, value : any) : Event {
  let w = window as any;
  if (w.CustomEvent) {
    return new CustomEvent(name, {
      detail  : {
        value : value
      },
      bubbles : true
    });
  } else {
    let e = document.createEvent("CustomEvent");
    e.initCustomEvent(name, true, true, {
      detail : {
        value : value
      }
    });
  }
}
