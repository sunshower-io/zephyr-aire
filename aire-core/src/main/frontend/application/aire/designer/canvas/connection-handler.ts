import {
  mxCellState,
  mxConnectionHandler,
}               from 'mxgraph/javascript/mxClient';
import {Canvas} from '@aire/designer/canvas/canvas';

export class DefaultConnectionHandler extends mxConnectionHandler {
  constructor(private canvas : Canvas) {
    super(canvas);
  }

  createEdgeState(a : any) : mxCellState {
    let e = this.canvas.createEdge(null, null, null, null, null);
    return new mxCellState(this.canvas.view, e, this.canvas.getCellStyle(e));
  }
}
