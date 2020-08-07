import * as _ from "lodash";

import {Graph, Node, Vertex} from "@aire/common/algorithms/graph/graph";

import {Layer, mxGraph, mxGraphModel} from "mxgraph";

import {Class, getClass} from "@aire/core/lang";

import {TaskGraph, Vertex as V} from "@aire/designer/model/graph";

import {Canvas} from "@aire/designer/canvas/canvas";
import {Edge}  from "@aire/designer/model/graph/edge";
import {
  ElementFactoryProvider,
  ElementLoader
}                 from "@aire/designer/canvas/palette";
import {Codec}    from "@aire/designer/codec/codec";
import {Drawable} from "@aire/designer/model/elements";

export interface Encoder {
  encode(t : Drawable) : {};
}

export class DefaultEncoder implements Encoder {
  encode(t : Drawable) : {} {
    let n = t as Layer,
      geo = t.geometry;
    return {
      id        : n.id,
      name      : (n as any).value,
      type      : "io.sunshower.gyre.api.model.VertexElement",
      reference : (n as any).reference,
      layout    : {
        x      : geo.x,
        y      : geo.y,
        width  : geo.width,
        height : geo.height
      },
      content   : {
        type : "io.sunshower.gyre.api.model.ContentElement"
        // reference: "definition"
      }
    };
  }
}

export interface Transformation<T> {
  apply(t : T) : T;
}

export interface EdgeFilter extends Transformation<Edge> {
}

export class ReversingTransformation implements EdgeFilter {
  apply(t : Edge) : Edge {
    let temp = t.source;
    t.source = t.target;
    t.target = temp;
    return t;
  }
}

export type FilterType = "edge" | "vertex";

export class JsonCodec implements Codec {
  transformations : { [key : string] : Transformation<any>[] };
  factories : Map<string, ElementFactoryProvider>;
  loaders : Map<string, ElementLoader>;
  encoders : Map<Class<any>, Encoder>;

  private defaultEncoder : Encoder;

  constructor() {
    this.encoders = new Map<Class<any>, Encoder>();
    this.setDefaultEncoder(new DefaultEncoder());
    this.registerTransformation("edge", new ReversingTransformation());
  }

  registerTransformation(key : FilterType, t : Transformation<any>) {
    this.transformations = this.transformations || {};
    this.transformations[key] = this.transformations[key] || [];
    this.transformations[key].push(t);
  }

  setDefaultEncoder(encoder : Encoder) {
    this.defaultEncoder = encoder;
  }

  register<T>(type : Class<T>, encoder : Encoder) : void {
    this.encoders.set(type, encoder);
  }

  registerProviderFactory(key : string, factory : ElementFactoryProvider) : void {
    if (!this.factories) {
      this.factories = new Map<string, ElementFactoryProvider>();
    }
    this.factories.set(key, factory);
  }

  registerLoader(key : string, loader : ElementLoader) : void {
    if (!this.loaders) {
      this.loaders = new Map<string, ElementLoader>();
    }
    this.loaders.set(key, loader);
  }

  resolveElementLoader(key : string, canvas : Canvas) : ElementLoader {
    if (this.loaders && this.loaders.has(key)) {
      return this.loaders.get(key);
    }
    return canvas.resolveElementLoader(key);
  }

  import(canvas : Canvas, graph : TaskGraph) : void {
    canvas.getModel().beginUpdate();
    try {
      let vertices = new Map<string, Drawable>();
      for (let vertex of graph.vertices) {
        if (!vertices.has(vertex.id)) {
          vertices.set(vertex.id, this.resolve(vertex, canvas));
        }
      }

      for (let edge of graph.edges) {
        let parent = vertices.get(edge.target),
          child = vertices.get(edge.source);
        if (edge.relationship === "parent") {
          parent.addChild(child);
        } else {
          canvas.insertEdge(
            canvas.getDefaultParent(),
            "",
            "",
            child,
            parent,
            "strokeColor=#4b738d;dashed=0;strokeWidth=1"
          );
        }
      }

      for (let v of vertices.values()) {
        v.addTo(canvas);
      }
    } finally {
      canvas.getModel().endUpdate();
    }
  }

  private resolve(v : V, canvas : Canvas) : Drawable {
    let el = this.resolveElementLoader(v.id, canvas);
    if (el == null) {
      el = this.resolveElementLoader(v.reference["target-id"], canvas);
    }
    return el.load(canvas, v);
  }

  private visit(
    cell : Layer,
    parent : Layer,
    vertices : any[],
    edges : any,
    encoders : Map<Class<any>, Encoder>,
    defaultParent : Layer
  ) {
    if (cell !== defaultParent && (cell as any).reference) {
      let encoder = encoders.get(getClass(cell)) || this.defaultEncoder;
      vertices.push(encoder.encode(cell as any));
      if (parent !== defaultParent) {
        edges.push({
          source       : cell.id,
          target       : cell.parent.id,
          relationship : "parent",
          type         : "io.sunshower.gyre.api.model.EdgeElement"
        });
      }

      if (cell.edges) {
        for (let edge of cell.edges) {
          if (edge.source.id != edge.target.id) {
            edges.push({
              source : edge.source.id,
              target : edge.target.id,
              type   : "io.sunshower.gyre.api.model.EdgeElement"
            });
          }
        }
      }
      if (cell.children) {
        for (let p of cell.children) {
          this.visit(p, cell, vertices, edges, encoders, defaultParent);
        }
      }
    }
  }

  export(model : mxGraphModel, canvas : mxGraph) : any {
    let cells = model.getChildCells(canvas.getDefaultParent(), true, true),
      encoders = this.encoders,
      vertices = [],
      defaultParent = canvas.getDefaultParent(),
      edges = [];

    for (let cell of cells) {
      if (cell.isVertex()) {
        this.visit(
          cell,
          defaultParent,
          vertices,
          edges,
          encoders,
          defaultParent
        );
      } else {
        if (cell.source.id != cell.target.id) {
          edges.push({
            source : cell.source.id,
            target : cell.target.id,
            type   : "io.sunshower.gyre.api.model.EdgeElement"
          });
        }
      }
    }
    return {vertices, edges};
  }

  private addEdges(es : any[], t : Vertex<any>) : void {
    for (let k in t.adjacencies) {
      let edge = t.adjacencies[k],
        e = {
          source       : edge.source.id,
          target       : edge.target.id,
          relationship : edge.relationship,
          type         : "io.sunshower.gyre.api.model.EdgeElement"
        };
      es.push(this.applyTransformations("edge", e));
    }
  }

  private applyTransformations(type : FilterType, e : any) {
    let tfs = this.transformations,
      ts = tfs && tfs[type];
    return ts && _.reduce(ts, (edge, tf) => tf.apply(edge), e);
  }
}

export class SerializationGraph extends Graph<any> {
}

interface GraphElement {
  id : string;
  value : Layer;
}
