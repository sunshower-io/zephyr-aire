import {
  TemplateResult,
  LitElement,
  html,
  customElement
} from 'lit-element';

@customElement('aire-fab')
class AireFab extends LitElement {


  render() : TemplateResult {
    return html`
      <slot></slot>
    `;

  }

}
