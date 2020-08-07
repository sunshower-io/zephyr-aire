import {html, LitElement, TemplateResult, customElement, PropertyValues} from "lit-element";
import Inject                                                            from "@aire/inject/inject";
import {DesignerManager}                                                 from "@aire/designer/elements/designer-manager";
import {Designer}                                                        from "@aire/designer/core/designer";
import {mxCell, mxDragSource, mxGeometry, mxUtils}                       from "mxgraph/javascript/mxClient";
import {Canvas}                                                          from "@aire/designer/canvas/canvas";

@customElement('aire-palette-element')
class AirePaletteElement extends LitElement {

  @Inject
  private designerManager : DesignerManager;


  constructor() {
    super();
  }


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
      cell.setVertex(true);
      console.log("CELL", cell);
      graph.importCells([cell], x, y, target);
    };

    mxUtils.makeDraggable(this, graphResolver as any, cellFunction, dragElt, null, null, true) as any;
  }


  public render() : TemplateResult {
    return html`
    <img src="/icons/icon-dark.svg" width="32px" height="32px"/>
    `;
  }
}



