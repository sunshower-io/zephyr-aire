import {TemplateResult, LitElement, html, property, customElement} from 'lit-element';

@customElement('aire-card')
class AireCard extends LitElement {




  render() : TemplateResult {
    return html`
      <header class="aire-header">
        <slot name="icon"></slot>
        <slot name="header"></slot>
      </section>
      <slot name="content"></slot>
      <footer class="aire-footer">
        <slot name="footer"></slot>
      </section>
    `
  }

}

