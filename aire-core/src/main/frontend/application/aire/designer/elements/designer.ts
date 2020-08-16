import {PolymerElement}                          from "@polymer/polymer/polymer-element";
import {StampedTemplate}                         from "@polymer/polymer/interfaces";
import {Designer}                                from "@aire/designer/core/designer";
import Inject                                    from "@aire/inject/inject";
import {DesignerManager}                         from "@aire/designer/core/designer-manager";
import {DefaultInnerGrid, DefaultOuterGrid}      from "@aire/designer/ext/grid";
import {mxEvent, mxGraphModel, mxCodec, mxUtils} from "mxgraph/javascript/mxClient";
import {Disposable}                              from "@aire/designer/core/resize-events";
import {registerEvent}                           from "@aire/core/events";
import {createEvent, dom}                        from "@aire/core/dom";
import siblings = dom.siblings;

class Model extends mxGraphModel {

}

class AireDesigner extends PolymerElement {


  /**
   * injected fields
   */
  @Inject
  private designerManager : DesignerManager;


  /**
   * @private
   */
  private graph : Designer;
  private resizeListenerRegistration : Disposable;


  constructor() {
    super();
  }

  static get properties() {
    return {


      /**
       * the context-unique identifier for this designer
       */
      id : String,


      model : {
        type     : Model,
        notify   : true,
        observer : 'onDesignerModelChanged'
      },

      /**
       * determine whether this graph allows connections between nodes
       */
      connectable : {
        type     : Boolean,
        notify   : true,
        observer : 'onConnectableChanged'
      },


      /**
       * determine whether the grid is enabled
       */
      gridEnabled : {
        type     : Boolean,
        notify   : true,
        observer : 'onGridEnabledChanged'
      },


      /**
       * determine whether guides are enabled for this grid
       */

      guidesEnabled : {
        type     : Boolean,
        notify   : true,
        observer : 'onGuidesEnabledChanged'
      },

      /**
       * determine whether nodes are snapped to this grid
       */

      gridSnapEnabled : {
        type     : Boolean,
        notify   : true,
        observer : 'onGridSnapEnabledChanged'
      }


    };
  }


  public set designerModel(model : mxGraphModel) {
    this.designer.model = model;
    (this as any).model = model;
  }


  public get designer() : Designer {
    return this.designerManager.focus(this.id);
  }

  public set designer(designer : Designer) {
    let dm = this.designerManager;
    this.graph = designer;
    dm.register(designer);
    dm.focus(this.id);
    this.designerModel = designer.model;
  }



  _attachDom(dom : StampedTemplate | null) : ShadowRoot | null {
    document.body.append(dom as any);
    return null;
  }

  focus(options? : FocusOptions) : void {
    super.focus(options);
  }


  connectedCallback() {
    this.resizeListenerRegistration = registerEvent(
      'resize',
      window,
      this.updateSize.bind(this),
      {timeout : 100}
    );
    this.updateSize();
    super.connectedCallback();
  }

  disconnectedCallback() {
    this.resizeListenerRegistration.dispose();
    super.disconnectedCallback();
  }

  ready() : void {
    let id = this.id,
      designer = this.designer;
    mxEvent.disableContextMenu(this);

    designer = !!designer ?
               new Designer(id, this, designer.getModel()) :
               new Designer(id, this);

    this.registerEvents(designer);
    this.designer = designer;
    super.ready();
  }


  private registerEvents(designer : Designer) : void {

    designer.model.addListener(mxEvent.CHANGE, (sender, event) => {
      let evt = createEvent('model-changed', {}),
        self = this as any;
      self.designerModel = designer.model;
      this.dispatchEvent(evt);
    });
  }

  /**
   * various handlers for implementing toolbars, etc.
   */

  onDesignerModelChanged(newValue : mxGraphModel, oldValue : mxGraphModel) : void {
    (this as any).$server.setModel(this.writeModel(newValue));
    this.designerModel = newValue;
  }

  private writeModel(model : mxGraphModel) : string {
    let encoder = new mxCodec(),
      node = encoder.encode(model),
      root = mxUtils.getXml(node),
      result = new XMLSerializer().serializeToString(node);
    return result;
  }



  private onGuidesEnabledChanged(newValue : boolean, oldValue : boolean) : void {
    this.designer.setGuidesEnabled(newValue);
  }

  private onGridSnapEnabledChanged(newValue : boolean, oldValue : boolean) : void {
    this.designer.setSnapEnabled(newValue);
  }

  private onGridEnabledChanged(newValue : boolean, oldValue : boolean) : void {
    let graph = this.designer;
    if (newValue) {
      graph.addGrids(DefaultInnerGrid, DefaultOuterGrid);
    } else {
      graph.removeGrids(DefaultInnerGrid, DefaultOuterGrid);
    }
  }

  private onConnectableChanged(newValue : boolean, oldValue : boolean) : void {
    this.designer.setConnectable(newValue);
  }


  /**
   * mxGraph doesn't play super well with non-absolute positioning or without specifying sizes for the
   * graph container, so we go through all the predecessors and successors of this DOM node, sum their heights/widths
   * and set the top/bottom/left/right css properties
   * @private
   */
  private updateSize() : void {
    let hsum = (sib : HTMLElement, i : [number, number]) => {
        let [h, w] = i;
        console.log(sib, sib.offsetWidth);
        return [h + sib.offsetHeight, w + sib.offsetWidth];
      },

      //todo: this works for flex-orientation: column but not for row yet
      [pheights,] =
        siblings.reducePrevious(this, [0, 0], hsum),
      [nheights,] = siblings.reduceNext(this, [0, 0], hsum);
    this.style.top = pheights + 'px';
    this.style.bottom = nheights + 'px';
  }

}


window.customElements.define('aire-designer', AireDesigner);

