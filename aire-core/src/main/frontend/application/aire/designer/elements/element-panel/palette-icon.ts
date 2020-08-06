import { customElement, containerless, autoinject } from 'aurelia-framework';
import { bindable } from 'aurelia-framework';
import { ElementFactory } from 'lib/designer/canvas/palette';
import { Canvas } from 'lib/designer/canvas/canvas';
import { AuthenticationContextHolder } from 'lib/common/security/model/token';

@autoinject
@containerless
@customElement('palette-icon')
export class PaletteIcon {
  @bindable
  canvas: Canvas;

  @bindable
  factory: ElementFactory;

  container: HTMLElement;

  element: HTMLElement;

  constructor(private contextHolder: AuthenticationContextHolder) {}

  private setEnabled(t: boolean): void {
    if (!t) {
      // $(this.container).addClass('disabled');
    }
  }

  attached() {
    if (this.element) {
      setTimeout(() => {
        this.factory.initialize(this.canvas, this.element);
      });
    } else {
      console.log('no element');
    }
    // this.contextHolder.hasPermission(this.factory).then(t => {
    //     if(t) {
    //         this.factory.initialize(this.canvas, this.element);
    //     }
    //     this.setEnabled(t)
    // });
  }
}
