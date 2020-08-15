import {
  mxGraph,
  mxGraphModel, mxRubberband
} from "mxgraph/javascript/mxClient";
import {Grid, GridOptions}                from "@aire/designer/ext/grid";
import {Disposable, registerGridListener} from "@aire/designer/core/resize-events";


export class Designer extends mxGraph implements Disposable {

  /**
   * the set of grids applied to this designer (may be null)
   * @private
   */
  private _grids : Grid[];

  /**
   *
   * @private the registration for grid resize events
   */
  private gridResizeEventRegistration : Disposable;


  /**
   *
   * @param id the ID
   * @param target
   * @param model
   */
  constructor(
    public readonly id : string,
    private readonly target : HTMLElement,
    model? : mxGraphModel
  ) {
    super(target, model);
    new mxRubberband(this);
    this.setPanning(true);
    this.setResizeContainer(false);
    this.panningHandler.useLeftButtonForPanning = false;
  }


  /**
   * clean up resources
   */

  public dispose() {
    if (this.gridResizeEventRegistration) {
      this.gridResizeEventRegistration.dispose();
    }
  }


  public get grids() : Grid[] {
    return this._grids = this._grids || [];
  }

  /**
   *
   * @param gridSpecs the grid specifications to add
   */
  public addGrids(...gridSpecs : GridOptions[]) {
    if (this.gridResizeEventRegistration) {
      this.gridResizeEventRegistration.dispose();
    }
    let grids = this.grids;
    for (let gridSpec of gridSpecs) {
      let grid = new Grid(this, gridSpec);
      grids.push(grid);
      grid.draw();
    }
    this.gridResizeEventRegistration = registerGridListener(this);
  }

  public removeGrids(...gridOpts : GridOptions[]) : void {
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
    if (!this.grids.length && this.gridResizeEventRegistration) {
      this.gridResizeEventRegistration.dispose();
    }
    return;
  }
}