export namespace Arrays {

  export function equals<T>(fst : T[], snd : T[]) : boolean {

    if (fst === snd) {
      return true;
    }
    if (fst && !snd) {
      return false;
    }
    if (snd && !fst) {
      return false;
    }

    if (fst.length !== snd.length) {
      return false;
    }

    let len = fst.length;
    while (len--) {
      if (fst[len] != snd[len]) {
        return false;
      }
    }
    return true;

  }

}