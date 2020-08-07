import {html, LitElement, PropertyValues, TemplateResult, customElement} from "lit-element";


@customElement('aire-palette')
class AirePalette extends LitElement {
  constructor() {
    super();
  }


  updated(vals : PropertyValues) {
    super.updated(vals);
  }


  dispatchAddElementEvent() : void {
    console.log("SUP");

  }

  render() : TemplateResult {
    return html`<h1 @click="${this.dispatchAddElementEvent}">Click me!</h1>`;
  }


}
