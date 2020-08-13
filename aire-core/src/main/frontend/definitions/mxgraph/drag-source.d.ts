
/// <reference path="./utils.d.ts" />
/// <reference path="./image.d.ts" />
/// <reference path="./geometry.d.ts" />

declare module "mxgraph/javascript/mxClient" {

    export type DropTarget = (x:number, y:number) => Layer;

    export class mxDragSource {

        constructor(h:HTMLElement, u:ImportFunction);


        dragOffset              : mxPoint;
        guidesEnabled           : boolean;

        gridEnabled             : boolean;

        autoscroll              : boolean;

        highlightDropTargets    : boolean;


        getGraphForEvent        : (event:mxEvent) => Canvas;


        createDragElement       : () => Node;

        createPreviewElement    : (canvas:Canvas) => Node;

        mouseMove(e:mxEvent)    : void;

        setGuidesEnabled(enabled:boolean) : void;

        dragEnter(c:Canvas, e:mxEvent): void;

        /**
         *
         * @param c
         * @param x
         * @param y
         * @param e
         */
        getDropTarget(c : Canvas, x:number, y:number, e:mxEvent) : any;

        /**
         *
         * @param graph
         * @param e
         * @param t
         * @param x
         * @param y
         */
        drop(
            graph:Canvas,
            e: mxEvent,
            t: Layer,
            x: number,
            y:number
        ) : Layer;
    }
}