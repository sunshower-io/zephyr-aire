import {Designer} from "@aire/designer/core/designer";
import {mxGraphModel, mxGraphView, mxPoint, mxRectangle, mxUtils} from "mxgraph/javascript/mxClient";
import {DesignerView} from "@aire/designer/core/ext/view";

const DefaultPageScrollTileSize = new mxRectangle(0, 0, 100, 100);

export class ExtendingDesigner extends Designer {

  public scrollTileSize : mxRectangle;

  constructor(id : string, target : HTMLElement, model? : mxGraphModel) {
    super(id, target, model);
    this.configurePanning();
  }


  private configurePanning() {
    this.setPanning(true);
    this.panningHandler.ignoreCell = true;
    this.scrollTileSize = DefaultPageScrollTileSize;
  }

  public getPagePadding() : mxPoint {
    let container = this.container;
    return new mxPoint(
      Math.max(0, Math.round(container.offsetWidth - 34)),
      Math.max(0, Math.round(container.offsetHeight - 34))
    );
  }


  public getPageSize() : mxRectangle {
    let sts = DefaultPageScrollTileSize || new mxRectangle(0, 0, 400, 400);
    return (this.pageVisible) ? new mxRectangle(0, 0, this.pageFormat.width * this.pageScale,
      this.pageFormat.height * this.pageScale
    ) : sts;
  }


  public getPageLayout() : mxRectangle {
    let bounds = this.getGraphBounds(),
      size = (this.pageVisible) ? this.getPageSize() : this.scrollTileSize;
    if (bounds.width == 0 || bounds.height == 0) {
      return new mxRectangle(0, 0, 1, 1);
    } else {

      let x = Math.ceil(bounds.x / this.view.scale - this.view.translate.x),
        y = Math.ceil(bounds.y / this.view.scale - this.view.translate.y),
        w = Math.floor(bounds.width / this.view.scale),
        h = Math.floor(bounds.height / this.view.scale),
        x0 = Math.floor(x / size.width),
        y0 = Math.floor(y / size.height),
        w0 = Math.ceil((x + w) / size.width) - x0,
        h0 = Math.ceil((y + h) / size.height) - y0;

      return new mxRectangle(x0, y0, w0, h0);
    }
  }

  public sizeDidChange() : void {
    if (this.container && mxUtils.hasScrollbars(this.container)) {
      let pages = this.getPageLayout(),
        pad = this.getPagePadding(),
        size = this.getPageSize(),

        minw = Math.ceil(2 * pad.x / this.view.scale + pages.width * size.width),
        minh = Math.ceil(2 * pad.y / this.view.scale + pages.height * size.height),

        min = this.minimumGraphSize;

      if (min == null || min.width != minw || min.height != minh) {
        this.minimumGraphSize = new mxRectangle(0, 0, minw, minh);
      }

      let dx = pad.x / this.view.scale - pages.x * size.width,
        dy = pad.y / this.view.scale - pages.y * size.height;

      if (!this.autoTranslate && (this.view.translate.x != dx || this.view.translate.y != dy)) {
        this.autoTranslate = true;
        this.view.x0 = pages.x;
        this.view.y0 = pages.y;

        let tx = this.view.translate.x,
          ty = this.view.translate.y;

        this.view.setTranslate(dx, dy);
        this.container.scrollLeft += (dx - tx) * this.view.scale;
        this.container.scrollTop += (dy - ty) * this.view.scale;

        this.autoTranslate = false;
        return;
      }
      super.sizeDidChange();

    }
  }

  public getPreferredPageSize(bounds : mxRectangle, width : number, height : number) : mxRectangle {
    let pages = this.getPageLayout(),
      size = this.getPageSize();
    return new mxRectangle(0, 0, pages.width * size.width, pages.height * size.height);
  }


  public createGraphView() : mxGraphView {
    return new DesignerView(this);
  }
}