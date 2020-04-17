import {
  TemplateResult,
  LitElement,
  html,
  customElement
} from 'lit-element';

@customElement('aire-card')
class AireCard extends LitElement {


  render() : TemplateResult {
    return html`
        <slot name="header"></slot>
        <slot name="content"></slot>
        <slot name="footer"></slot>
    `;
  }

}

