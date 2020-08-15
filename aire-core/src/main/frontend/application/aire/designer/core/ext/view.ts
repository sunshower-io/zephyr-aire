import {mxGraph, mxGraphView, mxRectangle, mxUtils} from "mxgraph/javascript/mxClient";
import {Designer}                                   from "@aire/designer/core/designer";
import {ExtendingDesigner}                          from "@aire/designer/core/ext/extending-designer";


export class DesignerView extends mxGraphView {

  constructor(graph : ExtendingDesigner) {
    super(graph);
  }


  validate() {

    if (this.graph.container != null && mxUtils.hasScrollbars(this.graph.container)) {
      let graph = this.graph as ExtendingDesigner,
        pad = graph.getPagePadding(),
        size = graph.getPageSize();

      // let tx = this.translate.x,
      //   ty = this.translate.y;
      this.translate.x = pad.x / this.scale - (this.x0 || 0) * size.width;
      this.translate.y = pad.y / this.scale - (this.y0 || 0) * size.height;
    }
    super.validate();


  }

  getBackgroundPageBounds() : mxRectangle {
    let graph = this.graph as ExtendingDesigner,
      layout = graph.getPageLayout(),
      page = graph.getPageSize();

    return new mxRectangle(
      this.scale * (this.translate.x + layout.x * page.width),
      this.scale * (this.translate.y + layout.y * page.height),
      this.scale * layout.width * page.width,
      this.scale * layout.height * page.height
    );
  }
}