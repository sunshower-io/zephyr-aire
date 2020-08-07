import {
  AbstractConstructorFactory,
  Descriptor
}                    from "@aire/designer/base/factory";
import {Drawable}    from "@aire/designer/model/elements";
import {GyreFactory} from "@aire/designer/gyre/element-factory";
import {Designer}    from "@aire/designer/core/designer";
import {
  mxCellOverlay,
  mxConstants,
  mxImage,
  mxPerimeter,
  mxPoint
}             from "mxgraph/javascript/mxClient";
import {Gyre} from "@aire/designer/gyre/gyre-element";

export class DefaultConstructorFactory extends AbstractConstructorFactory {
  static readonly style = "default-style";

  protected doRegister(designer : Designer) : void {
    let style = {};
    style[mxConstants.STYLE_FONTCOLOR] = "#FFFFFF";
    style[mxConstants.STYLE_SHAPE] = mxConstants.SHAPE_RECTANGLE;
    style[mxConstants.STYLE_FILLCOLOR] = "white";
    style[mxConstants.STYLE_FILL_OPACITY] = "100";
    style[mxConstants.STYLE_STROKECOLOR] = "#660066";
    style[mxConstants.STYLE_STROKEWIDTH] = "3px";
    style[mxConstants.STYLE_ROUNDED] = true;
    style[mxConstants.STYLE_PERIMETER] = mxPerimeter.RectanglePerimeter;
    style["labelPosition"] = "right";
    style["align"] = "left";
    designer
      .getCanvas()
      .getStylesheet()
      .putCellStyle("default-style", style);
  }

  construct(d : Descriptor) : Drawable {
    let e = d.loader as GyreFactory,
      n = new Gyre(e.taskName, d.x, d.y, 96, 48, d.id, d.taskId);
    n.labelClass = "small-label";
    // n.reference = e.reference;
    n.image = e.paletteIcon;
    if (d.isCreate && d.loader.currentDropTarget) {
      n.setParent(d.loader.currentDropTarget);
      // d.loader.currentDropTarget.addChild(n);
    }
    n.addOverlay(
      new mxCellOverlay(
        new mxImage(e.displayIcon, 24, 24),
        e.taskName,
        mxConstants.ALIGN_LEFT,
        mxConstants.ALIGN_MIDDLE,
        new mxPoint(24, 0)
      )
    );
    n.style = DefaultConstructorFactory.style;
    return n;
  }
}
