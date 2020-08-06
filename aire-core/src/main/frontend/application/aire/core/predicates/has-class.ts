
import {dom}       from "aire/core/dom";
import {Predicate} from 'aire/core/predicates';

declare module 'aire/core/predicates' {
  interface Predicate {
    hasClass(selector: string) : Predicate ;
  }
}


declare module 'aire/core/dom' {
  export interface dom {
    hasClass(selector : string) : Predicate;
  }
}

dom.prototype.hasClass = (selector: string): Predicate => {
  let r = new HasClassPredicate(selector) as any;
  return r as Predicate;
};


Predicate.prototype.hasClass = (selector: string) : Predicate => {
  this.branches.push(new HasClassPredicate(selector));
  return (this as any) as Predicate;
};


class HasClassPredicate {
  constructor(readonly selector: string) {

  }

  apply(e: Element) : boolean {
    return e.classList.contains(this.selector);
  }
}
