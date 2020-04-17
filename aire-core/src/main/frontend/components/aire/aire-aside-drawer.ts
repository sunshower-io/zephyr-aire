import {
  TemplateResult,
  LitElement,
  html,
  customElement
} from 'lit-element';

@customElement('aire-aside')
class AireAsideDrawer extends LitElement {

  render() : TemplateResult {
    return html`
      <nav>
        <slot name="menu"></slot>
      </nav>
      <slot name="content"></slot>
    `;
  }
}

