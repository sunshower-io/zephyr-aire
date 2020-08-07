import {html, LitElement, PropertyValues, TemplateResult, customElement} from "lit-element";


@customElement('aire-palette')
class AirePalette extends LitElement {
  constructor() {
    super();
  }


  updated(vals : PropertyValues) {
    super.updated(vals);
  }

  render() : TemplateResult {
    return html`<h1><slot></slot></h1>`;
  }


}
