import { Action } from '@aire/designer/canvas/action';
import { Canvas } from '@aire/designer/canvas/canvas';
export class RedoAction implements Action {
  name: string = 'redo';
  run(canvas: Canvas): void {
    canvas.historyManager.redo();
  }
}
