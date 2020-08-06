import { LayerNode } from './layer-panel';
import { bindable, containerless, customElement } from 'aurelia-framework';
import { createEvent } from 'aire/core/dom';
import { DesignerManager } from '../../core/designer-manager';
import { mxEvent } from 'mxgraph';

@containerless
@customElement('layer-collapsible')
export class LayerCollapsible {
  @bindable
  private layer: LayerNode;

  glyph = 'fa fa-chevron-circle-down';
  visible = 'fa fa-eye';
  hidden = 'fa fa-eye-slash';

  closed = 'fa fa-chevron-circle-down';
  open = 'fa fa-chevron-circle-right';

  expandedGlyph = this.open;

  expanded: boolean = true;
  visGlyph: string = this.visible;

  contents: HTMLSpanElement;

  selected: boolean;
  private source: boolean;
  checkSelected: (a, b) => void;

  constructor(private manager: DesignerManager) {}

  toggleAndCenter() {
    let designer = this.manager.getCurrentCanvas(),
      model = designer.getSelectionModel(),
      cell = this.layer,
      layer = cell.layer;
    if (model.isSelected(layer)) {
      model.removeCell(layer);
    } else {
      model.addCell(layer);
    }
    this.source = true;
  }

  attached(): void {
    let designer = this.manager.getCurrentCanvas(),
      model = designer.getSelectionModel(),
      cell = this.layer.layer,
      source = this.source,
      listener = (a, b) => {
        this.selected = model.isSelected(cell);
        if (
          source &&
          model.cells &&
          model.cells.length === 1 &&
          model.cells[0] === cell
        ) {
          this.source = false;
          designer.scrollCellToVisible(cell, true);
        }
      };
    listener.apply(this);
    this.checkSelected = listener;
    model.addListener(mxEvent.CHANGE, listener);
  }

  toggleVisibility(e: any) {
    e.stopPropagation();
    let vg = this.visGlyph,
      invisible = vg !== this.visible,
      designer = this.manager.getCurrentCanvas();
    this.visGlyph = invisible ? this.visible : this.hidden;
    designer.toggleCells(invisible, [this.layer.layer], true);
  }

  detached(): void {
    let designer = this.manager.getCurrentCanvas(),
      model = designer.getSelectionModel();
    model.removeListener(this.checkSelected as any);
  }

  toggle(e: any): void {
    e.stopPropagation();
    this.expanded = !this.expanded;
    this.expandedGlyph = this.expanded ? this.closed : this.open;
  }
}
