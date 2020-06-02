import {
  TemplateResult,
  LitElement,
  html,
  customElement
} from 'lit-element';

@customElement('aire-media-card')
class AireMediaCard extends LitElement {


  render() : TemplateResult {
    return html`
        <div class="aire-card aire-media-card">
          <slot name="media"></slot>
          <slot name="header"></slot>
          <slot name="content"></slot>
          <slot name="footer"></slot>
        </div>
    `;
  }

}

