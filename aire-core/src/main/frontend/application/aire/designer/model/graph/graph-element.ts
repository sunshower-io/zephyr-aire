import {Edge}   from "@aire/designer/model/graph/edge";
import {Vertex} from "@aire/designer/model/graph/vertex";
import {Property} from "@aire/condensation/condensation";

export class CanvasLayout {
  x : number;
  y : number;
  translateX : number;
  translateY : number;
}

export class TaskGraph {
  @Property({alias : "edges", type : Edge})
  edges : Edge[];

  @Property({alias : "vertices", type : Vertex})
  vertices : Vertex[];

  layout : CanvasLayout;

  constructor(o? : any) {
    // if (o.vertices) {
    //     for (let i = 0; i < o.vertices.length; i++) {
    //         this.vertices.push(new Vertex(o.vertices[i]))
    //     }
    // }
  }

  addEdge(e : Edge) : void {
    if (!this.edges) {
      this.edges = [];
    }
    this.edges.push(e);
  }

  addVertex(v : Vertex) : void {
    if (!this.vertices) {
      this.vertices = [];
    }
    this.vertices.push(v);
  }
}
