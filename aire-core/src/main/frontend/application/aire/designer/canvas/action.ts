import {Canvas} from "@aire/designer/canvas/canvas";

export interface Action {
  name : string;

  run(canvas : Canvas) : void;
}
