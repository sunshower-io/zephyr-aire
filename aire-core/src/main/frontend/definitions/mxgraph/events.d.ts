declare module "mxgraph/javascript/mxClient" {

    export class mxEvent {

        static readonly CELLS_RESIZED           : string;

        static readonly CELLS_ADDED             : string;
        static readonly CELLS_REMOVED           : string;
        static readonly UNDO                    : string;
        static readonly ADD                     : string;
        static readonly CLEAR                   : string;
        static readonly REDO                    : string;
        static readonly CHANGE                  : string;
        static readonly CLICK                   : string;
        static readonly GROUP_CELLS             : string;
        static readonly DOUBLE_CLICK            : string;

        static readonly SELECT                  : string;

        consume() : void;
        static getSource(e:Event) : HTMLElement;

        getProperty(key:string) : any;



    }

    export module mxEvent {

      function getClientX(event:mxEvent): number;
      function getClientY(event: mxEvent): number;
      function addMouseWheelListener(any:any): void;
      function consume(any:any) : void;
      function disableContextMenu(container: HTMLElement) : void;
    }

    export class mxEventObject extends mxEvent {

        /**
         *
         * @param type
         * @param g
         * @param group
         * @param b
         * @param border
         * @param c
         * @param cells
         */
        constructor(
            type:string,
            g:string,
            group:Layer,
            b: string,
            border: number,
            c: string,
            cells:Layer[]
        );
    }

}