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

  public addGrids(...gridSpecs : GridOptions[]) : void {
    let grids = this.grids;
    for (let gridSpec of gridSpecs) {
      let grid = new Grid(this, gridSpec);
      grids.push(grid);
      grid.draw();
    }
  }

  removeGrids(...gridOpts : GridOptions[]) : void {
    let grids = this.grids,
      removed = 0,
      len = (grids && grids.length) || 0,
      toRemove = gridOpts.length;

    while (len--) {
      for (let grid of gridOpts) {
        let g = grids[len];
        if (g && g.hasOptions(grid)) {
          g.destroy();
          grids.splice(len, 1);
          removed++;
          if (removed === toRemove) {
            return;
          }
        }
      }
    }
    return;
  }
}