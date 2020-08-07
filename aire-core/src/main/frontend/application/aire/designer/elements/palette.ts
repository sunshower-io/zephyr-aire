import {html, LitElement, PropertyValues, TemplateResult, customElement} from "lit-element";
import {createEvent}                                                     from "@aire/core/dom";


@customElement('aire-palette')
class AirePalette extends LitElement {
  constructor() {
    super();
  }


  updated(vals : PropertyValues) {
    super.updated(vals);
  }


  dispatchAddElementEvent() : void {
    let event = createEvent('add-element', {});
    this.dispatchEvent(event);

  }

  render() : TemplateResult {
    return html`<h1 @click="${this.dispatchAddElementEvent}">Click me!</h1>`;
  }


}
