declare module "mxgraph/javascript/mxClient" {

    export class State {

    }
    export class Canvas {

    }

    export class mxSvgCanvas2D {

        root:XMLDocument;


        constructor(root:XmlDocument);

        writeDefaults() : void;
    }

    export class mxImageExport {

        includeOverlays         : boolean;


        drawState(state:State, canvas:Canvas) : void;


    }
}