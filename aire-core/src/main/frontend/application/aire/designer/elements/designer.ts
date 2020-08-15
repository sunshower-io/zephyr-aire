import {PolymerElement}                     from "@polymer/polymer/polymer-element";
import {StampedTemplate}                    from "@polymer/polymer/interfaces";
import {Designer}                           from "@aire/designer/core/designer";
import Inject                               from "@aire/inject/inject";
import {DesignerManager}                    from "@aire/designer/core/designer-manager";
import {DefaultInnerGrid, DefaultOuterGrid} from "@aire/designer/ext/grid";
import {ExtendingDesigner}                  from "@aire/designer/core/ext/extending-designer";
import {mxEvent}                            from "mxgraph/javascript/mxClient";
import {Disposable}                         from "@aire/designer/core/resize-events";
import {debounce}                           from "@aire/core/lang";
import {registerEvent}                      from "@aire/core/events";


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
      id          : String,
      connectable : {
        type     : Boolean,
        notify   : true,
        observer : 'onConnectableChanged'
      },
      gridEnabled : {
        type     : Boolean,
        notify   : true,
        observer : 'onGridEnabledChanged'
      }
    };
  }


  public get designer() : Designer {
    return this.designerManager.focus(this.id);
  }

  public set designer(designer : Designer) {
    let dm = this.designerManager;
    this.graph = designer;
    dm.register(designer);
    dm.focus(this.id);
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

    this.designer = !!designer ?
                    new Designer(id, this, designer.getModel()) :
                    new Designer(id, this);
    super.ready();
  }

  /**
   * various handlers for implementing toolbars, etc.
   */

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

  private updateSize() : void {


  }

}


window.customElements.define('aire-designer', AireDesigner);

