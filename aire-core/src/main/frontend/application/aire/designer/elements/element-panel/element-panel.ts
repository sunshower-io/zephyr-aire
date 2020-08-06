import { Canvas } from 'lib/designer/canvas/canvas';

import { ElementFactory } from 'lib/designer/canvas/palette';

import { customElement, bindable, autoinject } from 'aurelia-framework';
import { DesignerManager } from 'lib/designer/core';
import { PaletteGroup } from 'lib/designer/base/model';

@autoinject
@customElement('element-panel')
export class ElementPanel {
  @bindable
  public active: boolean = true;

  @bindable
  private loading: boolean;

  title: string;

  @bindable
  public model: PaletteGroup;

  private factories: ElementFactory[];

  private canvas: Canvas;

  isAttached: boolean;

  constructor(private readonly designerManager: DesignerManager) {}

  attached(): void {
    this.loading = true;
    this.isAttached = true;
    this.refresh();
  }

  detached(): void {
    this.isAttached = false;
  }

  private async refresh() {
    this.canvas = this.designerManager.getCurrentCanvas();
    let model = this.model;
    if (model) {
      model.listen('loading').forEach(async t => {
        this.loading = true;
      });
      model.listen('loaded').forEach(t => {
        this.loading = false;
        this.factories = t.factories;
      });

      let r = this.model.factory;
      if (r) {
        r.register(this.canvas);
        this.factories = await r.load();
        this.title = r.name;
      } else {
        this.loading = true;
      }
    }
    // this.loading = false;
  }
}
