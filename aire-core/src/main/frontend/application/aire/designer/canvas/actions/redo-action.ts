import { Action } from '../action';
import { Canvas } from '../canvas';
export class RedoAction implements Action {
  name: string = 'redo';
  run(canvas: Canvas): void {
    canvas.historyManager.redo();
  }
}
