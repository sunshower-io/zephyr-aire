import {html, LitElement, TemplateResult, customElement, PropertyValues, property} from "lit-element";
import {
  GraphResolver,
  mxCell,
  mxConstants,
  mxEvent,
  mxGeometry,
  mxUtils
}                                                                                  from "mxgraph/javascript/mxClient";
import {Element, ElementPayload}                                                   from "@aire/designer/core/element";
import {ImageTypes}                                                                from "@aire/designer/core/image";
import {ElementConverter}                                                          from "@aire/designer/core/element-converter";
import {Property}                                                                  from "@aire/inject/convert-property";
import {locateGraph}                                                               from "@aire/designer/utilities/graph-locator";
import {DefaultElementFactory}                                                     from "@aire/designer/core/element-factory";

@customElement('aire-palette-element')
class AirePaletteElement extends LitElement {


  @Property({
    converter : ElementConverter
  })

  private model : Element;


  constructor() {
    super();
  }

  protected firstUpdated(_changedProperties : PropertyValues) {
    super.firstUpdated(_changedProperties);
    new DefaultElementFactory(this.model, this);
  }


  public render() : TemplateResult {
    let el = this.model,
      image = el.getImage(ImageTypes.Palette);
    console.log(this.model);
    return html`
      <img src="${image.source}"
          width="${image.width === -1 ? undefined : image.width}"
          height="${image.height === -1 ? undefined : image.height}"
        />
      `;
  }

  // <!--    <img src="${this.model.paletteIcon}" width="32px" height="32px"/>-->
}



