import { Designer } from './designer';
import { autoinject } from 'aurelia-framework';
import { Canvas, CanvasEvent } from 'lib/designer/canvas/canvas';
import { EventAggregator } from 'aurelia-event-aggregator';
import { Observable } from 'rxjs';
import { ConstraintManager } from 'lib/designer/base/dependencies';

export type DesignerChangedEvent = { previous: Designer; current: Designer };

@autoinject
export class DesignerManager {
  private currentDesigner: Designer;

  loading: boolean = true;
  public selectedCell: { id: string } & any;
  public constraintManager: ConstraintManager;

  public currentModelId: string;
  public onCellSelected: Observable<CanvasEvent<any>>;

  constructor(public readonly eventBus: EventAggregator) {}

  fire(key: string): void {
    this.getCurrentCanvas().fire(key);
  }

  cellSelected(e: CanvasEvent<any>): void {
    this.selectedCell = e.data;
  }

  setCurrent(designer: Designer) {
    let previous = this.currentDesigner;
    this.onCellSelected = designer.getCanvas().listen('cell-selected');
    this.currentDesigner = designer;
    if (this.loading) {
      this.currentDesigner.setLoading();
    } else {
      this.currentDesigner.removeLoading();
    }
    this.eventBus.publish('on-designer-changed', {
      previous: previous,
      current: designer
    });
    this.onCellSelected.forEach(this.cellSelected.bind(this));
  }

  public getCurrent(): Designer {
    return this.currentDesigner;
  }

  public getCurrentCanvas(): Canvas {
    return this.currentDesigner.getCanvas();
  }

  undo(): void {
    this.currentDesigner.undo();
  }

  redo(): void {
    this.currentDesigner.redo();
  }

  toggleLoading(): void {
    this.loading = !this.loading;
    if (!this.loading && this.currentDesigner) {
      this.currentDesigner.removeLoading();
    }
  }

  setConstraintManager(manager: ConstraintManager) {
    this.constraintManager = manager;
  }
}
