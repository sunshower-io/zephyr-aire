import {Canvas}    from "../canvas";
import {JsonCodec} from "../../codec/json-codec";

export class SaveAction {
  name : string = "save";

  run(canvas : Canvas) : void {
    let g = new JsonCodec().export(canvas.getModel(), canvas);
    // canvas.dispatch({
    //   type : "canvas-saved",
    //   data : g
    // });
  }
}
