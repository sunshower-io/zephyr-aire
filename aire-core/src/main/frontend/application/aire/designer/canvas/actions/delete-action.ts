import { Action } from '@aire/designer/canvas/action';
import { Canvas } from '@aire/designer/canvas/canvas';
export class DeleteSelectionAction implements Action {
  name: string = 'delete-selection';
  run(canvas: Canvas): void {
    canvas.getModel().beginUpdate();
    try {
      let cells = canvas.getSelectionCells();
      if (cells && cells.length) {
        canvas.removeCells(cells);
      }
    } finally {
      canvas.getModel().endUpdate();
    }
  }
}
