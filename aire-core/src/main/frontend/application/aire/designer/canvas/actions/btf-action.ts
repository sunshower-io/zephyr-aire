import { Action } from '../action';
import { Canvas } from '../canvas';

export class BringToFront implements Action {
  name: string;

  run(canvas: Canvas): void {
    canvas.getModel().beginUpdate();
    try {
      canvas.orderCells(false, canvas.getSelectionCells());
    } finally {
      canvas.getModel().endUpdate();
    }
  }
}

export class SendToBack implements Action {
  name: string;

  run(canvas: Canvas): void {
    canvas.getModel().beginUpdate();
    try {
      canvas.orderCells(true, canvas.getSelectionCells());
    } finally {
      canvas.getModel().endUpdate();
    }
  }
}
