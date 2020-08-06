import {
  bindable,
  customElement,
  containerless,
  children
} from 'aurelia-framework';

import { Panel } from './panel';

@customElement('panels')
export class Panels {
  @bindable
  @children('panel')
  private children: Panel[];

  private panel: HTMLElement;

  private current: Panel;

  attached(): void {}

  open(id: string) {
    return this.openChild(this.children, id);
  }

  private openChild(children: Panel[], id: string) {
    if (this.current) {
      this.current.active = false;
    }
    let panel = children.find(t => t.id == id);
    panel.active = true;
    this.current = panel;
    // $(this.panel).tabs('select_tab', id);
    return true;
  }

  childrenChanged(nv: Panel[], ov: Panel[]) {
    // $(this.panel).tabs();
    this.openChild(nv, nv[0].id);
  }
}
