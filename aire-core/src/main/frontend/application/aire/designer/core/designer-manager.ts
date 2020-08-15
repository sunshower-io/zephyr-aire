import {singleton} from "tsyringe";
import {Designer}  from "@aire/designer/core/designer";


@singleton()
export class DesignerManager {

  private _focused : Designer;

  private designers : Map<string, Designer>;


  constructor() {
    this.designers = new Map<string, Designer>();
  }

  public set focused(designer : Designer) {
    this._focused = designer;
  }

  public get focused() : Designer {
    return this._focused || [...this.designers][this.designers.size - 1][1];
  }

  public register(designer : Designer) : Designer {
    let existing = this.designers.get(designer.id);
    if (existing) {
      this.unregister(existing.id);
      existing.dispose();
    }
    this.designers.set(designer.id, designer);
    return existing;
  }

  public unregister(id : string) : Designer {
    let designer = this.designers.get(id);
    this.designers.delete(id);
    return designer;
  }

  public focus(id : string) : Designer {
    let designer = this.designers.get(id);
    this.focused = designer;
    return designer;
  }


}
