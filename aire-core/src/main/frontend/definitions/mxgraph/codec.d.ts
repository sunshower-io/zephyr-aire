declare module "mxgraph/javascript/mxClient" {

    export class mxCodec {

        encode(model: mxGraphModel) : any;

    }

    export module mxCodecRegistry {
        function registerCodec(codec:mxObjectCodec) : void;
    }

    export class mxObjectCodec {
        constructor(data:any);

    }
}