import {Canvas} from "@aire/designer/canvas";

export interface Action {
  name : string;

  run(canvas : Canvas) : void;
}
