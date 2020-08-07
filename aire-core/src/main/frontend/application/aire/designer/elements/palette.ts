import {html, LitElement, PropertyValues, TemplateResult, customElement} from "lit-element";
import {createEvent}                               from "@aire/core/dom";
import {mxCell, mxDragSource, mxGeometry, mxUtils} from "mxgraph/javascript/mxClient";
import Inject                                      from "@aire/inject/inject";
import {DesignerManager}                                                 from "@aire/designer/elements/designer-manager";
import {Designer}                                                        from "@aire/designer/core/designer";


@customElement('aire-palette')
class AirePalette extends LitElement {


  @Inject
  private designerManager : DesignerManager;


  constructor() {
    super();
  }


  dispatchAddElementEvent(id : string) : void {
    let event = createEvent('add-element', {});
    this.dispatchEvent(event);


  }

  public render() : TemplateResult {
    return html`
        <div>
          <slot></slot>
        </div>`;
  }


}
