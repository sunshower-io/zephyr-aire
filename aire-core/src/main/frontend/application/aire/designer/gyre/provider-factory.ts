import {
  doLoad,
  ElementFactory,
  ElementFactoryProvider, Palette
}               from '../canvas/palette';
import {Canvas} from '../canvas/canvas';


import * as _              from 'lodash';
import {ConstraintManager} from '../base/dependencies';
import {GyreFactory}       from './element-factory';

export class GyreProviderFactory implements ElementFactoryProvider {
  icon : string;

  name : string;

  constructor(
    private palette : Promise<Palette[]>,
    private manager : ConstraintManager
  ) {
  }

  async load() : Promise<ElementFactory[]> {
    let palettes = await this.palette,
      items = _.flatMap(palettes, t => t.items),
      factories = items.map(
        t => new GyreFactory(t, this.manager)
      );
    this.name = palettes && palettes.length && palettes[0].name;
    return Promise.resolve(factories);
  }

  register(canvas : Canvas) : Promise<Canvas> {
    return doLoad(this, canvas);
  }
}
