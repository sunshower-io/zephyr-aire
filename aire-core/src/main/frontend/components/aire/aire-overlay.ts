import {
  TemplateResult,
  LitElement,
  html,
  customElement, css
} from 'lit-element';

@customElement('aire-overlay')
class AireOverlay extends LitElement {


  static get styles() {
    return css`
    .aire-overlay-upper {
      display:flex;
      flex-direction: row;
      justify-content: space-between;
    }
    `
  }


  render() : TemplateResult {
    return html`
        <div class="aire-overlay">
            <div class="aire-overlay-upper">
                <slot name="header"></slot>
                <slot name="close"></slot>
            </div>
            <slot name="content"></slot>
            <slot name="footer"></slot>
        </div>
    `;
  }

}

