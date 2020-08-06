import {
  child,
  bindable,
  customElement,
  autoinject,
  containerless,
  useShadowDOM
} from 'aurelia-framework';
import { AbstractPanel, PanelMode } from './abstract-panel';

@containerless
@customElement('left-panel')
export class DesignerLeftPanel extends AbstractPanel {
  attached() {}
}
