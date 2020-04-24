import {
  TemplateResult,
  LitElement,
  html,
  customElement, css
} from 'lit-element';

@customElement('aire-icon-card')
class AireIconCard extends LitElement {


  static get styles() {
    return css`
    .aire-icon-card .aire-icon-card-upper {
      display: flex;
      justify-content: space-between; 
    }
    `
  }

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

