import {
  TemplateResult,
  LitElement,
  html,
  customElement
} from 'lit-element';

@customElement('aire-icon-card')
class AireIconCard extends LitElement {


  render() : TemplateResult {
    return html`
        <div class="aire-card aire-icon-card">
          <div class="aire-icon-card-upper">
            <slot name="icon"></slot>
            <div class="aire-icon-card-right">
              <slot name="header"></slot>
              <slot name="content"></slot>
            </div>  
          </div>
          <slot name="footer"></slot>
        </div>
    `;
  }

}

