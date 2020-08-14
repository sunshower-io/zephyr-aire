import {Element}     from "@aire/designer/core/element";
import {
  ImportFunction,
  mxCell,
  mxConstants,
  mxDragSource,
  mxEvent,
  mxGeometry,
  mxGraph,
  mxUtils
}                    from "mxgraph/javascript/mxClient";
import {locateGraph} from "@aire/designer/utilities/graph-locator";
import {ImageTypes}  from "@aire/designer/core/image";

export type CellSelector = (
  graph : mxGraph,
  el : Element,
  x : number,
  y : number
) => mxCell;

export interface ElementFactory {

  template : Element;

  importElement(
    graph : mxGraph,
    event : mxEvent,
    x : number,
    y : number,
    parent ? : mxCell
  ) : mxCell;
}


export class DefaultElementFactory implements ElementFactory {


  dragSource : mxDragSource;
  dragElement : HTMLElement;

  constructor(
    readonly template : Element,
    readonly sourceElement : HTMLElement,
    readonly selector ? : CellSelector
  ) {
    let dragElement = this.createDragElement();
    this.dragElement = dragElement;
    this.dragSource = mxUtils.makeDraggable(sourceElement, locateGraph, (graph : mxGraph, event : mxEvent, target : mxCell, x : number, y : number) => {
      let parent = (selector && selector(graph, template, x, y)) || target;
      this.importElement(graph, event, x, y, parent);
    }, dragElement, null, null, true);
  }


  importElement(
    graph : mxGraph,
    event : mxEvent,
    x : number,
    y : number,
    parent : mxCell,
  ) : mxCell {

    let geometry = new mxGeometry(0, 0, 120, 120),
      template = this.template,
      cell = new mxCell('coolbeans', geometry);
    console.log(this.template);

    let image = template.getImage(ImageTypes.Element) || template.getImage(ImageTypes.Palette),
      source = image && image.source;

    cell.setStyle(`${mxConstants.STYLE_IMAGE}=${source};${mxConstants.STYLE_SHAPE}=${mxConstants.SHAPE_IMAGE}`);
    cell.setVertex(true);
    graph.importCells([cell], x, y, parent);

    return cell;
  }

  createDragElement() : HTMLElement {
    let dragElt = document.createElement('div');
    dragElt.style.border = 'dashed black 1px';
    dragElt.style.width = '120px';
    dragElt.style.height = '40px';
    return dragElt;
  }


}