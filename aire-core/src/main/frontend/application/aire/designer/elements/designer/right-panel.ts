import { customElement, containerless } from 'aurelia-framework';

@containerless
@customElement('right-panel')
export class DesignerRightPanel {
  panelClosed: boolean;

  attached() {}

  togglePanel() {
    this.panelClosed = !this.panelClosed;
  }
}
