import { autoinject, customElement, containerless } from 'aurelia-framework';

import { Canvas } from 'lib/designer/canvas';

import { Designer } from 'lib/designer/core';
import { DesignerManager } from 'lib/designer/core';
import { EventAggregator } from 'aurelia-event-aggregator';

@autoinject
@containerless
@customElement('designer')
export class DesignerElement {
  private canvas: Canvas;
  private designer: Designer;
  private element: HTMLElement;

  constructor(
    private eventBus: EventAggregator,
    private designerManager: DesignerManager
  ) {}

  activate(): void {
    this.designerManager.setCurrent(this.designer);
  }

  deactivate(): void {}

  attached(): void {
    let designer = new Designer(this.eventBus, this.element),
      canvas = designer.getCanvas();
    this.setCanvas(canvas);
    this.setDesigner(designer);
    this.designerManager.setCurrent(designer);
    this.designer.activate();
  }

  detached(): void {
    this.designer.deactivate();
  }

  public setCanvas(canvas: Canvas): void {
    this.canvas = canvas;
  }

  public setDesigner(designer: Designer): void {
    this.designer = designer;
  }
}
