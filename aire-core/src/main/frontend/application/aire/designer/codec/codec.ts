import { Encoder } from './json-codec';
import { Canvas } from 'lib/designer/canvas/canvas';
import {
  ElementFactoryProvider,
  ElementLoader
} from 'lib/designer/canvas/palette';
import { TaskGraph } from 'lib/designer/model/graph/graph-element';
import { Class } from 'aire/core/lang';

export interface Codec {
  register<T>(type: Class<T>, encoder: Encoder);

  registerLoader(key: string, loader: ElementLoader): void;

  registerProviderFactory(key: string, factory: ElementFactoryProvider): void;

  import(canvas: Canvas, graph: TaskGraph): void;
}
