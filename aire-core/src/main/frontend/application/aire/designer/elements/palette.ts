import {html, LitElement, PropertyValues, TemplateResult, customElement} from "lit-element";
import {createEvent}                                                     from "@aire/core/dom";


@customElement('aire-palette')
class AirePalette extends LitElement {



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
