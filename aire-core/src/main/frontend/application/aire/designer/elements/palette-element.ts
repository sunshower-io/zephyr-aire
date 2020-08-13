import {html, LitElement, TemplateResult, customElement, PropertyValues, property} from "lit-element";
import {
  GraphResolver,
  mxCell,
  mxConstants,
  mxEvent,
  mxGeometry,
  mxUtils
}                                                                                  from "mxgraph/javascript/mxClient";
import {Element, ElementPayload}                                                   from "@aire/designer/core/element";
import {ImageTypes}                                                                from "@aire/designer/core/image";
import {ElementConverter}                                                          from "@aire/designer/core/element-converter";
import {Property}                                                                  from "@aire/inject/convert-property";
import {locateGraph}                                                               from "@aire/designer/utilities/graph-locator";

@customElement('aire-palette-element')
class AirePaletteElement extends LitElement {


  @Property({
    converter : ElementConverter
  })
  private model : Element;


  constructor() {
    super();
  }


  // resolveGraph : GraphResolver = (event : mxEvent) => {
  //   return this.designerManager.resolveFirst().getCanvas();
  // };


  protected firstUpdated(_changedProperties : PropertyValues) {
    super.firstUpdated(_changedProperties);

    let dragElt = document.createElement('div');
    dragElt.style.border = 'dashed black 1px';
    dragElt.style.width = '120px';
    dragElt.style.height = '40px';
    let cellFunction = (graph, event, target, x, y) => {
      let cell = new mxCell("Whatever", new mxGeometry(0, 0, 120, 40));
      cell.setStyle(`${mxConstants.STYLE_IMAGE}=${this.model.getImage(ImageTypes.Palette).source};${mxConstants.STYLE_SHAPE}=${mxConstants.SHAPE_IMAGE}`);
      cell.setVertex(true);
      graph.importCells([cell], x, y, target);
    };
    mxUtils.makeDraggable(this,
      locateGraph, cellFunction, dragElt, null, null, true
    ) as any;
  }


  public render() : TemplateResult {
    let el = this.model,
      image = el.getImage(ImageTypes.Palette);
    return html`
      <img src="${image.source}"
          width="${image.width === -1 ? undefined : image.width}"
          height="${image.height === -1 ? undefined : image.height}"
        />
      `;
  }

  // <!--    <img src="${this.model.paletteIcon}" width="32px" height="32px"/>-->
}



