import { Action } from '@aire/designer/canvas/action';
import { Canvas } from '@aire/designer/canvas/canvas';
export class UndoAction implements Action {
  name: string = 'undo';
  run(canvas: Canvas): void {
    canvas.historyManager.undo();
  }
}
