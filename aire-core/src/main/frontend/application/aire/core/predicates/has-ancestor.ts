import {Predicate} from 'aire/core/predicates';
import {dom}       from "aire/core/dom";

declare module 'aire/core/predicates' {
  interface Predicate {
    hasAncestor(selector : string) : Predicate;

  }
}



declare module 'aire/core/dom' {

  export interface dom {
    hasAncestor(selector: string) : Predicate;
  }
}

dom.prototype.hasAncestor = (selector: string): Predicate => {
  let r = new HasAncestorPredicate(selector) as any;
  return r as Predicate;
};


Predicate.prototype.hasAncestor = (selector : string) : Predicate => {
  let e = new HasAncestorPredicate(selector) as any;
  this.branches.push(e);
  return (this as any) as Predicate;
};

class HasAncestorPredicate {

  constructor(readonly selector : string) {

  }

  apply(e : Element) : boolean {
    for (let c = e, selector = this.selector; !!c; c = c.parentElement) {
      if (c.classList.contains(selector)) {
        return true;
      }
    }
    return false;
  }
}