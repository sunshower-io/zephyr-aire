import {Encoder} from "./json-codec";
import {Canvas}    from "../canvas/canvas";
import {
  ElementFactoryProvider,
  ElementLoader
}                  from "../canvas/palette";
import {TaskGraph} from "../model/graph/graph-element";
import {Class}     from "../../core/lang";

export interface Codec {
  register<T>(type : Class<T>, encoder : Encoder);

  registerLoader(key : string, loader : ElementLoader) : void;

  registerProviderFactory(key : string, factory : ElementFactoryProvider) : void;

  import(canvas : Canvas, graph : TaskGraph) : void;
}
