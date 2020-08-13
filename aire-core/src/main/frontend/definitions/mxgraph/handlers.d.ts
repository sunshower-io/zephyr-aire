declare module "mxgraph/javascript/mxClient" {
    export class mxGraphHandler {

        graph                       : mxGraph;
        gridEnabled                 : boolean;
        guidesEnabled               : boolean;

        constructor(graph:mxGraph);

        /**
         *
         * @param remove
         */
        setRemoveCellsFromParent(remove:boolean) : void;

        /**
         *
         * @param self
         */
        getInitialCellForEvent(self:mxGraphHandler) : Layer;


        getCells(cells:Layer) : Layer[];

        /**
         * @param cells
         * @param dx
         * @param dy
         * @param clone
         * @param target
         * @param event
         */
        moveCells(
            cells:Layer[],
            dx:number,
            dy:number,
            clone:boolean,
            target:mxCell,
            event:mxEvent
        ) : void;
    }


    export class mxVertexHandler {

        graph:mxGraph;

        constrainGroupByChildren: boolean;


        constructor(state:mxCellState);

        /**
         *
         * @param cell
         * @param dx
         * @param dy
         * @param index
         * @param gridEnabled
         * @param constrained
         * @param recurse
         */
        resizeCell(cell: Layer,
                   dx: number,
                   dy: number,
                   index: number,
                   gridEnabled: boolean,
                   constrained: boolean,
                   recurse: boolean) : void;

        /**
         *
         * @param cell
         * @param dx
         * @param dy
         */
        moveChildren(cell:Layer, dx:number, dy:number) : void;
    }
}