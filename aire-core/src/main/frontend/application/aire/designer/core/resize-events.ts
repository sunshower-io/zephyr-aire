import {Designer} from "@aire/designer/core/designer";
import {debounce} from "@aire/core/lang";

export interface Disposable {
  dispose() : void;

}

export function registerGridListener(designer : Designer) : Disposable {
  let resizeListener = debounce(function () {
    for (let grid of designer.grids) {
      grid.draw();
    }
  }, 100);
  window.addEventListener('resize', resizeListener);
  return {
    dispose() {
      window.removeEventListener('resize', resizeListener);
    }
  };


}