import {
  AbstractConstructorFactory,
  Descriptor
}                                                     from '@aire/designer/base/factory';
import {Drawable}                                     from '@aire/designer/model/elements';
import {GyreFactory}                                  from '@aire/designer/gyre/element-factory';
import {GyreContainerNode}                            from '@aire/designer/gyre/container-node';
import {Designer}                                     from '@aire/designer/core/designer';
import {mxCellOverlay, mxConstants, mxImage, mxPoint} from 'mxgraph/javascript/mxClient';

export class ContainerConstructorFactory extends AbstractConstructorFactory {
  static readonly style = 'container-style';

  protected doRegister(designer : Designer) : void {
    let style = {};
    style[mxConstants.STYLE_FILL_OPACITY] = 40;
    style[mxConstants.STYLE_FILLCOLOR] = '#FFFFFF';
    style[mxConstants.STYLE_VERTICAL_LABEL_POSITION] = 'bottom';
    style[mxConstants.STYLE_VERTICAL_ALIGN] = 'top';
    designer
      .getCanvas()
      .getStylesheet()
      .putCellStyle(ContainerConstructorFactory.style, style);
  }

  construct(d : Descriptor) : Drawable {
    let e = d.loader as GyreFactory,
      n = new GyreContainerNode(
        e.taskName,
        d.x,
        d.y,
        d.width || 400,
        d.height || 200,
        d.id,
        d.taskId
      );

    n.addOverlay(
      new mxCellOverlay(
        new mxImage(e.displayIcon, 24, 24),
        e.taskName,
        mxConstants.ALIGN_LEFT,
        mxConstants.ALIGN_TOP,
        new mxPoint(32, 0)
      )
    );
    // n.reference = e.reference;
    n.style = ContainerConstructorFactory.style;
    return n;
  }
}
