import {
  mxGraph,
  mxGraphModel
}                          from "mxgraph/javascript/mxClient";
import {Grid, GridOptions} from "@aire/designer/ext/grid";

export class Designer extends mxGraph {

  public id : string;
  private _grids : Grid[];

  constructor(id : string, target : HTMLElement, model? : mxGraphModel) {
    super(target, model);
    this.id = id;
  }


  public get grids() : Grid[] {
    return this._grids = this._grids || [];
  }

  public addGrid(gridSpec : GridOptions) : void {
    let grids = this.grids,
      grid = new Grid(this, gridSpec);
    grids.push(grid);
    grid.draw();
  }

  removeGrid(grid : GridOptions) : GridOptions {
    let grids = this.grids,
      len = (grids && grids.length) || 0;
    while (len--) {
      let g = grids[len];
      if (g.hasOptions(grid)) {

        g.destroy();
        grids.splice(len, 1);
        return grid;
      }
    }
    return null;
  }
}