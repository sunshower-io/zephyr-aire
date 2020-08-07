import {Canvas}         from "@aire/designer/canvas/canvas";
import {ImportFunction} from "mxgraph/javascript/mxClient";

import {Drawable} from "@aire/designer/model/elements";

import "velocity";
import {Vertex}                from "@aire/designer/model/graph/vertex";
import {ProtectedObject, Role} from "@aire/common/security/model/user";
import {CanvasUtilities}   from "@aire/designer/canvas/utils/canvas-utilities";
import {ConstraintManager} from "@aire/designer/base/dependencies";

declare let Velocity : any;

export interface ElementLoader {
  load(model : Canvas, v : Vertex) : Drawable;
}

export async function doLoad(
  factory : ElementFactoryProvider,
  canvas : Canvas
) : Promise<Canvas> {
  let a = await factory.load();
  a.forEach(t => {
    t.preInitialize(canvas);
    canvas.registerProvider(t);
  });
  return canvas;
}

export interface ElementFactoryProvider {
  icon : string;

  load() : Promise<ElementFactory[]>;

  register(canvas : Canvas) : Promise<Canvas>;
}

export interface ElementFactory extends ProtectedObject {
  categories : string[];
  canvasIconMode : IconMode;
  paletteIconMode : IconMode;
  rolesDenied : Role[];
  rolesAllowed : Role[];
  elementName : string;
  displayIcon : string;
  paletteIcon : string;
  currentDropTarget : Drawable;
  importFunction : ImportFunction;
  constraintManager : ConstraintManager;

  resolveElementLoader(key : string) : ElementLoader;

  handles(key : string) : boolean;

  preInitialize(canvas : Canvas) : void;

  initialize(canvas : Canvas, element : HTMLElement) : void;

  newElement(
    x : number,
    y : number,
    event : Event,
    canvas : Canvas,
    target : any
  ) : Drawable;

  setDropTarget(l : Drawable) : void;

  isHostableBy(c : Drawable) : boolean;
}

export type CellFactory = (factory : ElementFactory) => ImportFunction;

export let DefaultCellFactory : CellFactory = (factory : ElementFactory) => {
  return (canvas : Canvas, event : any, target : any, x : number, y : number) => {
    if (canvas.canImportCell(target)) {
      canvas.getModel().beginUpdate();
      let renderable : Drawable = null;
      try {
        renderable = factory.newElement(x, y, event, canvas, target);
        if (renderable) {
          renderable.addTo(canvas);
        }
      } finally {
        canvas.getModel().endUpdate();
      }
      canvas.setSelectionCell(renderable);
    }
  };
};

export type IconMode = "font" | "image";

export abstract class DefaultElementFactory
  implements ElementFactory, ProtectedObject {
  categories : string[];
  canvasIconMode : IconMode = "font";
  paletteIconMode : IconMode = "font";
  elementName : string;
  displayIcon : string;
  paletteIcon : string;
  rolesAllowed : Role[] = [];
  rolesDenied : Role[] = [];

  currentDropTarget : Drawable;
  constraintManager : ConstraintManager;

  importFunction : ImportFunction;

  constructor() {
    this.importFunction = DefaultCellFactory(this);
  }

  setDropTarget(t : Drawable) : void {
    this.currentDropTarget = t;
  }

  isHostableBy(c : Drawable) : boolean {
    return false;
  }

  handles(key : string) : boolean {
    return false;
  }

  resolveElementLoader(key : string) : ElementLoader {
    return null;
  }

  abstract newElement(
    x : number,
    y : number,
    event : Event,
    canvas : Canvas,
    target : any
  ) : Drawable;

  preInitialize(canvas : Canvas) : void {
  }

  initialize(canvas : Canvas, element : HTMLElement) : void {
    let image = this.createInitialImage(),
      dragSource = CanvasUtilities.makeDraggable(element, canvas, this, image);

    const [fst, snd] = this.createAnimation();
    (dragSource as any).createDragElement = () => {
      let i = image.cloneNode(true);
      Velocity(i, fst, snd);
      return i;
    };
  }

  createInitialImage() : HTMLElement {
    let image : HTMLImageElement = document.createElement("img");
    image.src = this.paletteIcon;
    image.width = 37;
    image.height = 37;
    return image;
  }

  createAnimation() : [any, any] {
    return [
      {
        scale : 2
      },
      {
        duration : 300,
        delay    : 250
      }
    ];
  }
}

export class Palette {
  public canvas : Canvas;
  public name: string;


  constructor(public factoryProvider : ElementFactoryProvider) {
  }

  public bind(canvas : Canvas) : void {
    this.canvas = canvas;
  }
}
