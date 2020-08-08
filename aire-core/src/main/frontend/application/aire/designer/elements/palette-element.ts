import {html, LitElement, TemplateResult, customElement, PropertyValues, property} from "lit-element";
import Inject                                                                      from "@aire/inject/inject";
import {DesignerManager}                                                           from "@aire/designer/elements/designer-manager";
import {
  GraphResolver,
  mxCell,
  mxConstants,
  mxDragSource,
  mxEvent,
  mxGeometry,
  mxUtils
}                                                                                  from "mxgraph/javascript/mxClient";
import {Canvas}                                                                    from "@aire/designer/canvas/canvas";
import {ModelElement}                                                              from "@aire/designer/model/model";

@customElement('aire-palette-element')
class AirePaletteElement extends LitElement {

  @Inject
  private designerManager : DesignerManager;

  @property()
  private model : ModelElement;

  constructor() {
    super();
  }


  resolveGraph : GraphResolver = (event : mxEvent) => {
    return this.designerManager.resolveFirst().getCanvas();
  };


  protected firstUpdated(_changedProperties : PropertyValues) {
    super.firstUpdated(_changedProperties);

    let img = document.createElement('img');
    img.src = "/icons/icon-dark.svg";
    img.width = 32;
    img.height = 32;

    let dragElt = document.createElement('div');
    dragElt.style.border = 'dashed black 1px';
    dragElt.style.width = '120px';
    dragElt.style.height = '40px';

    let graphResolver = (event) : Canvas => {
      return this.designerManager.resolveFirst().getCanvas();
    };

    let cellFunction = (graph, event, target, x, y) => {
      let cell = new mxCell("Whatever", new mxGeometry(0, 0, 120, 40));
      cell.setStyle(`${mxConstants.STYLE_IMAGE}=${this.model.paletteIcon};${mxConstants.STYLE_SHAPE}=${mxConstants.SHAPE_IMAGE}`);
      cell.setVertex(true);
      graph.importCells([cell], x, y, target);
    };

    mxUtils.makeDraggable(this, this.resolveGraph, cellFunction, dragElt, null, null, true) as any;
  }


  public render() : TemplateResult {
    return html`
    <img src="${this.model.paletteIcon}" width="32px" height="32px"/>
    `;
  }
}



