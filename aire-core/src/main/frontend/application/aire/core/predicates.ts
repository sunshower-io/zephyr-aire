export class Predicate {


  constructor(protected readonly el : Element, protected readonly branches : Predicate[] = []) {

  }


  iterate(arg : (e : Element) => void) {
    let e = this.el;
    if (this.apply(e)) {
      arg.apply(e);
    }
  }


  apply(e : Element) : boolean {
    return this.branches.every(t => t.apply(e));
  }
}

export interface Predicate {
  apply(e : Element) : boolean;
}
