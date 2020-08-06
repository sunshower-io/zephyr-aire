import {Predicate} from "aire/core/predicates";
import {dom}       from "aire/core/dom";


declare module 'aire/core/dom' {

  interface dom {
    and(...predicates: Predicate[]) : Predicate;
  }
}

declare module 'aire/core/predicates' {
  interface Predicate {
    and(...predicates: Predicate[]) : Predicate;
  }

  interface Predicate {
    or(...predicates:Predicate[]) : Predicate;
  }


}



Predicate.prototype.and = (...predicates:Predicate[]) : Predicate  => {
  this.branches.push(new AndPredicate(...predicates));
  return (this as any as Predicate);
};



Predicate.prototype.or = (...predicates: Predicate[]) : Predicate => {
  this.branches.push(new OrPredicate(...predicates));
  return this as any as Predicate;
};


class OrPredicate extends Predicate {
  constructor(...branches: Predicate[]) {
    super(null, branches);
  }

  apply(e: Element) : boolean {
    return !!this.branches.find(t => t.apply(e));
  }
}
class AndPredicate extends Predicate {
  constructor(...branches: Predicate[]) {
    super(null, branches);
  }

  apply(e: Element) : boolean {
    return this.branches.every(t => t.apply(e));
  }
}