import {Canvas} from "./canvas";

export interface Action {
  name : string;

  run(canvas : Canvas) : void;
}
