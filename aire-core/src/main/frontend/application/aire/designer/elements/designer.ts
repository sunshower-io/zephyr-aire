import {PolymerElement}                     from "@polymer/polymer/polymer-element";
import {StampedTemplate}                    from "@polymer/polymer/interfaces";
import {Designer}                           from "@aire/designer/core/designer";
import Inject                               from "@aire/inject/inject";
import {DesignerManager}                    from "@aire/designer/core/designer-manager";
import {DefaultInnerGrid, DefaultOuterGrid} from "@aire/designer/ext/grid";


let COUNT = 0;

class AireDesigner extends PolymerElement {

  private graph : Designer;


  @Inject
  private designerManager : DesignerManager;


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

  ready() : void {
    let id = this.id,
      designer = this.designer;
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


}


window.customElements.define('aire-designer', AireDesigner);

