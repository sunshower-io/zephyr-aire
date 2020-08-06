import {
  doLoad,
  ElementFactory,
  ElementFactoryProvider
} from 'lib/designer/canvas/palette';
import { Canvas } from 'lib/designer/canvas/canvas';

import { Palette, PaletteEvent } from 'lib/designer/base/model';
import { Subject } from 'rxjs';

import * as _ from 'lodash';
import { ConstraintManager } from 'lib/designer/base/dependencies';
import { GyreFactory } from './element-factory';
import { DesignerManager } from 'lib/designer/core/designer-manager';

export class GyreProviderFactory implements ElementFactoryProvider {
  icon: string;

  name: string;

  constructor(
    private palette: Promise<Palette[]>,
    private subject: Subject<PaletteEvent>,
    private manager: ConstraintManager,
    private designerManager: DesignerManager
  ) {}

  async load(): Promise<ElementFactory[]> {
    let palettes = await this.palette,
      items = _.flatMap(palettes, t => t.items),
      factories = items.map(
        t => new GyreFactory(t, this.manager, this.designerManager)
      );
    this.name = palettes && palettes.length && palettes[0].name;
    this.subject.next({
      state: 'loaded',
      providerFactory: this,
      factories: factories
    });
    return Promise.resolve(factories);
  }

  register(canvas: Canvas): Promise<Canvas> {
    return doLoad(this, canvas);
  }
}
