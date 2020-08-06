import {Encoder}   from "./json-codec";
import {Canvas}    from "@aire/designer/canvas/canvas";
import {
  ElementFactoryProvider,
  ElementLoader
}                  from "@aire/designer/canvas/palette";
import {TaskGraph} from "@aire/designer/model/graph/graph-element";
import {Class}     from "@aire/core/lang";

export interface Codec {
  register<T>(type : Class<T>, encoder : Encoder);

  registerLoader(key : string, loader : ElementLoader) : void;

  registerProviderFactory(key : string, factory : ElementFactoryProvider) : void;

  import(canvas : Canvas, graph : TaskGraph) : void;
}
