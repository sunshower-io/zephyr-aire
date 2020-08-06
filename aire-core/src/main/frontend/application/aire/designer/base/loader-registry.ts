import { Item } from './model';
import { ConstructorFactory } from './factory';
import {
  DesignerChangedEvent,
  DesignerManager
} from 'lib/designer/core/designer-manager';
import { Subscription } from 'aurelia-event-aggregator';

export class LoaderRegistry {
  private readonly registry = new Map<string, ConstructorFactory>();

  private subscription: Subscription;

  constructor(readonly manager: DesignerManager) {
    this.subscription = manager.eventBus.subscribe(
      'on-designer-changed',
      this.performLifecycle.bind(this)
    );
  }

  initialize() {
    let manager = this.manager,
      current = manager.getCurrent(),
      reg = this.registry,
      vals = reg && reg.values();
    if (vals && current) {
      for (let val of vals) {
        val.initialize(current);
      }
    }
  }

  protected performLifecycle(event: DesignerChangedEvent) {
    if (this.subscription) {
      for (let v of this.registry.values()) {
        if (event.previous) {
          v.dispose(event.previous);
        }
        if (event.current) {
          v.initialize(event.current);
        }
      }
    }
  }

  public register(pname: string, ctorFactory: ConstructorFactory) {
    this.registry.set(pname, ctorFactory);
  }

  resolve(item: Item): ConstructorFactory {
    let props = item.properties,
      reg = this.registry;
    if (props) {
      for (let p of props) {
        let r = reg.get(p.key);
        if (r) {
          return r;
        }
      }
    }
    return reg.get('default');
  }
}
