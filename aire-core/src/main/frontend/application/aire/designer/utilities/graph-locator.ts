import {mxEvent, mxGraph} from "mxgraph/javascript/mxClient";


export function locateGraph(event : mxEvent) : mxGraph {
  let x = mxEvent.getClientX(event),
    y = mxEvent.getClientY(event),
    element = document.elementFromPoint(x, y);


  if (element) {
    for(let e = element; e; e = e.parentElement) {
      for(let v of Object.values(e)) {
        if(v instanceof mxGraph) {
          return v;
        }
      }
    }
  }
  return null;

}