declare module "mxgraph/javascript/mxClient" {

    type mxCellStyle = {[key:string]:string};


    export class mxStylesheet {
        constructor();
        styles: {[key:string]:mxCellStyle};

        createDefaultEdgeStyle() : mxCellStyle;

        createDefaultVertexStyle() : mxCellStyle;


        putDefaultVertexStyle(style:mxCellStyle) : void;

        putDefaultEdgeStyle(style:mxCellStyle) : void;

        getDefaultEdgeStyle() : mxCellStyle;

        getDefaultVertexStyle() : mxCellStyle;


        putCellStyle(name:string, style:mxCellStyle) : void;

        getCellStyle(name:string, defaultStyle?:mxCellStyle) :  mxCellStyle;

    }
}