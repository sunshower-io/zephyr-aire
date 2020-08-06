import { Action } from '../action';
import { Canvas } from '../canvas';
export class UndoAction implements Action {
  name: string = 'undo';
  run(canvas: Canvas): void {
    canvas.historyManager.undo();
  }
}
