import { mxGraph, mxGraphView, mxRectangle, mxUtils } from 'mxgraph';

export class View extends mxGraphView {
  constructor(private graph: mxGraph) {
    super(graph);
  }

  getBackgroundPageBounds(): mxRectangle {
    let layout = this.graph.getPageLayout(),
      page = this.graph.getPageSize();

    return new mxRectangle(
      this.scale * (this.translate.x + layout.x * page.width),
      this.scale * (this.translate.y + layout.y * page.height),
      this.scale * layout.width * page.width,
      this.scale * layout.height * page.height
    );
  }

  validate(): void {
    if (
      this.graph.container != null &&
      mxUtils.hasScrollbars(this.graph.container)
    ) {
      let pad = this.graph.getPagePadding(),
        size = this.graph.getPageSize(),
        tx = this.translate.x,
        ty = this.translate.y;
      this.translate.x = pad.x / this.scale - (this.x0 || 0) * size.width;
      this.translate.y = pad.y / this.scale - (this.y0 || 0) * size.height;
    }

    super.validate();
  }
}
