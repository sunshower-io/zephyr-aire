import {
  MouseListener,
  mxCellState,
  mxConstants,
  mxEvent,
  mxGraph,
  mxMouseEvent,
  mxUtils,
  Style
}               from 'mxgraph/javascript/mxClient';
import {Canvas} from './canvas';

export class DefaultHoverListener implements MouseListener {
  private previousStyle : Style;
  private currentState : mxCellState;

  constructor(private graph : Canvas) {
  }

  dragEnter(event : mxEvent, state : mxCellState) : void {
    if (state && state.shape) {
      this.previousStyle = state.style;
      state.style = mxUtils.clone(state.style);
      this.updateStyle(state, true);
      state.shape.apply(state);
      state.shape.redraw();

      if (state.text) {
        state.text.apply(state);
        state.text.redraw();
      }
    }
  }

  dragLeave(event : mxEvent, state : mxCellState) : void {
    if (state && state.shape) {
      state.style = this.previousStyle;
      this.updateStyle(state, false);
      state.shape.apply(state);
      state.shape.redraw();

      if (state.text != null) {
        state.text.apply(state);
        state.text.redraw();
      }
    }
  }

  mouseDown(sender : mxGraph, event : mxMouseEvent) : void {
    if (this.currentState) {
      this.dragLeave(event.getEvent(), this.currentState);
      this.currentState = null;
    }
  }

  mouseUp(sender, a) : void {
  }

  mouseMove(sender : mxGraph, me : mxMouseEvent) : void {
    if (this.currentState && me.getState() == this.currentState) {
      return;
    }

    let graph = this.graph,
      tmp = graph.view.getState(me.getCell());
    if (graph.isMouseDown || (tmp && !graph.getModel().isVertex(tmp.cell))) {
      tmp = null;
    }
    if (tmp != this.currentState) {
      if (this.currentState) {
        this.dragLeave(me.getEvent(), this.currentState);
      }
      this.currentState = tmp;
      if (this.currentState) {
        this.dragEnter(me.getEvent(), this.currentState);
      }
    }
  }

  updateStyle(state : mxCellState, hover : boolean) {
    if (hover) {
      state.style[mxConstants.STYLE_STROKECOLOR] = '#254033';
    }
    state.style[mxConstants.STYLE_STROKEWIDTH] = hover ? '2' : '1';
  }
}
