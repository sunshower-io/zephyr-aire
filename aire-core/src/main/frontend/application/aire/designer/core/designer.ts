import {Point2D}     from '@aire/common/math';
import {Canvas}      from '@aire/designer/canvas/canvas';
import {CanvasModel} from '@aire/designer/model/model';
import {UndoAction} from '@aire/designer/canvas/actions/undo-action';
import {RedoAction}     from '@aire/designer/canvas/actions/redo-action';
import {TaskGraph}      from '@aire/designer/model/graph/graph-element';
import {DesignerLoader}        from '@aire/designer/core/loader';
import {Codec}                 from '@aire/designer/codec/codec';
import {DeleteSelectionAction}    from '@aire/designer/canvas/actions/delete-action';
import {BringToFront, SendToBack} from '@aire/designer/canvas/actions/btf-action';
import {mxRubberband}             from "mxgraph/javascript/mxClient";

export class Designer {
  private codec : Codec;
  private canvas : Canvas;
  private loading : boolean;

  private loader : DesignerLoader;

  constructor(
    private readonly container : HTMLElement,
    private readonly model : CanvasModel = new CanvasModel(),
    options? : DesignerOptions
  ) {
    let canvas = new Canvas(container, model);
    new mxRubberband(canvas);
      // selector = new CanvasSelector(canvas, container);
    this.canvas = canvas;

    // this.canvas.register(
    //   {
    //     group: 'file',
    //     key: 'save-all',
    //     name: 'save',
    //     values: ['ctrl', 's']
    //   },
    //   new SaveAction()
    // );

    this.canvas.register(
      {
        key    : 'bring-front',
        group  : 'object',
        name   : 'bring-front',
        values : ['ctrl', 'l']
      },
      new BringToFront()
    );

    this.canvas.register(
      {
        group  : 'object',
        key    : 'send-back',
        name   : 'send-back',
        values : ['ctrl', 'k']
      },
      new SendToBack()
    );

    this.canvas.register(
      {
        group  : 'edit',
        key    : 'delete-selected',
        name   : 'delete',
        values : ['delete']
      },
      new DeleteSelectionAction()
    );

    this.canvas.register(
      {
        group  : 'edit',
        key    : 'undo',
        name   : 'undo',
        values : ['ctrl', 'z']
      },
      new UndoAction()
    );

    this.canvas.register(
      {
        group  : 'edit',
        key    : 'redo',
        name   : 'redo',
        values : ['ctrl', 'y']
      },
      new RedoAction()
    );
  }

  // public getMenu(cfg : MenuConfig, roots : string[]) : Menu {
  //   return new Menu(this.getMenuItems(), cfg, roots);
  // }
  //
  // register(chord : Chord, action : Action) : void {
  //   this.canvas.register(chord, action);
  // }
  //
  // getMenuItems() : Chord[] {
  //   return this.canvas.keyHandler.getChords();
  // }

  setCodec(c : Codec) {
    this.codec = c;
  }

  activate() : void {
    this.canvas.activate();
  }

  deactivate() : void {
    this.canvas.deactivate();
  }

  setGraph(graph : TaskGraph) : void {
    if (!this.codec) {
      throw new Error('No codec provided');
    }
    this.codec.import(this.canvas, graph);
  }

  undo() : void {
    this.canvas.undo();
  }

  redo() : void {
    this.canvas.redo();
  }

  getCanvas() : Canvas {
    return this.canvas;
  }

  setLoading() : void {
    this.loader = new DesignerLoader(this.container);
    this.loader.setLoading();
  }

  removeLoading() : void {
    // this.loader.removeLoading();
  }
}

export class DesignerOptions {
  width? : number;
  height? : number;

  relativeLocation? : Point2D;
}
