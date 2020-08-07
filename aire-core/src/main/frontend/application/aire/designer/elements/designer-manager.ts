import {injectable, singleton} from "tsyringe";
import {Designer}              from "@aire/designer/core/designer";


@singleton()
export class DesignerManager {

  private readonly registeredDesigners : Map<string, Designer>;

  constructor() {
    this.registeredDesigners = new Map<string, Designer>();
  }


  public unregister(id : string) : Designer {
    console.log(`unregistering ${id}`);
    let designer = this.registeredDesigners.get(id);
    this.registeredDesigners.delete(id);
    return designer;
  }


  public register(id : string, designer : Designer) {
    console.log(`registering ${id}`);
    this.registeredDesigners.set(id, designer);
  }

  resolve(id : string) : Designer {
    return this.registeredDesigners.get(id);
  }

  resolveFirst() : Designer {
    for (let [_, v] of this.registeredDesigners) {
      console.log("Resolved " + v);
      return v;
    }
  }
}