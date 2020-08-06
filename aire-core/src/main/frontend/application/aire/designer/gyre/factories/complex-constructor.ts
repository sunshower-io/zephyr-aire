import {
  AbstractConstructorFactory,
  Descriptor
} from 'lib/designer/base/factory';
import { Drawable, RenderableElement } from 'lib/designer/model/elements';
import { GyreFactory } from 'lib/designer/gyre/element-factory';
import { Designer } from 'lib/designer/core/designer';
import {
  mxCellOverlay,
  mxConstants,
  mxImage,
  mxPerimeter,
  mxPoint
} from 'mxgraph';
import { Gyre } from '../gyre-element';
import { ComplexGyre } from '../complex-gyre';

export class ComplexConstructorFactory extends AbstractConstructorFactory {
  static readonly style = 'complex-style';

  protected doRegister(designer: Designer): void {
    let style = {};
    style[mxConstants.STYLE_FONTCOLOR] = '#FFFFFF';
    style[mxConstants.STYLE_SHAPE] = mxConstants.SHAPE_RECTANGLE;
    style[mxConstants.STYLE_FILLCOLOR] = 'white';
    style[mxConstants.STYLE_FILL_OPACITY] = 100;
    style[mxConstants.STYLE_STROKECOLOR] = '#660066';
    style[mxConstants.STYLE_STROKEWIDTH] = '3px';
    style[mxConstants.STYLE_ROUNDED] = true;
    style[mxConstants.STYLE_PERIMETER] = mxPerimeter.RectanglePerimeter;
    style['labelPosition'] = 'right';
    style['align'] = 'left';
    designer
      .getCanvas()
      .getStylesheet()
      .putCellStyle('complex-style', style);
  }

  construct(d: Descriptor): Drawable {
    let e = d.loader as GyreFactory,
      n = new Gyre(e.taskName, d.x, d.y, 96, 48, d.id);
    n.labelClass = 'small-label';
    n.reference = e.reference;
    n.image = e.paletteIcon;
    n.addOverlay(
      new mxCellOverlay(
        new mxImage(e.displayIcon, 24, 24),
        e.taskName,
        mxConstants.ALIGN_LEFT,
        mxConstants.ALIGN_MIDDLE,
        new mxPoint(24, 0)
      )
    );
    n.style = ComplexConstructorFactory.style;
    return n;
  }
}
