import { Traversal, Node, Edge, Graph, Vertex, copy } from './graph';

import * as _ from 'lodash';

export interface Sequence<T> {
  elements: { [key: string]: Vertex<T> };
}

export class CGraph<T> extends Graph<T> {}

export class ParallelSchedule<T> implements Traversal<Sequence<T>[], T> {
  copyF = (e: Vertex<T>) => new Node<T>(e.id, (e as any) as T);

  run(g: Graph<T>): Sequence<T>[] {
    let graph = this.pluck(copy(g, new CGraph<T>(), this.copyF)),
      keyspace = this.computeKeyspace(graph).filter(
        ks => graph.nodes[ks.id] == null
      );

    for (let key of keyspace) {
      graph.add(this.copyF(key));
    }

    let results = [],
      existing = {};
    while (true) {
      let ordered = this.roots(graph.nodes, existing);
      for (let o of ordered) {
        existing[o.id] = true;
      }
      if (ordered.length === 0) {
        break;
      } else {
        results.push({
          elements: ordered
        });
        this.updateAdjacencies(graph, ordered);
      }
    }
    return results;
  }

  private pluck(g: Graph<T>): Graph<T> {
    for (let nkey in g.nodes) {
      let node = g.nodes[nkey];
      node.removeSuccessor(node);
    }
    return g;
  }

  private updateAdjacencies(graph: Graph<T>, ordered: Vertex<T>[]) {
    for (let nkey in graph.nodes) {
      let node = graph.nodes[nkey];
      if (ordered.indexOf(node) < 0) {
        for (let adj of ordered) {
          node.removeSuccessor(adj);
        }
      }
    }
  }

  private computeKeyspace(g: Graph<T>): Vertex<T>[] {
    return _.uniqBy(
      _.values<Vertex<T>>(g.nodes).concat(
        _.flatMap(_.values<Vertex<T>>(g.nodes), node =>
          _.values<Edge<T>>(node.adjacencies).map(a => a.target)
        )
      ),
      e => e.id
    );
  }

  public roots(
    nodes: { [p: string]: Vertex<T> },
    existing?: { [p: string]: boolean }
  ): Vertex<T>[] {
    existing = existing || {};
    let adjacentNodes = _.reduce(
      _.flatMap<Vertex<T>>(_.values(nodes), n =>
        _.values<Edge<T>>(n.adjacencies).map(a => a.source)
      ),
      (u, t) => {
        u[t.id] = t;
        return u;
      },
      new Map<string, Vertex<T>>()
    );
    return _.values<Vertex<T>>(nodes).filter(
      n => adjacentNodes[n.id] == null && existing[n.id] == null
    );
  }
}

export class TopologicalSort<T> implements Traversal<Vertex<T>[], T> {
  run(g: Graph<T>): Vertex<T>[] {
    let keys = Object.keys(g.nodes),
      size = keys.length;
    if (size > 0) {
      let marked = {},
        stack = [],
        nodes = g.nodes;
      for (let k in nodes) {
        this.traverse(marked, stack, nodes[k]);
      }
      return stack;
    }
  }

  private traverse(marked: {}, stack: Array<Vertex<T>>, vertex: Vertex<T>) {
    if (!marked[vertex.id]) {
      marked[vertex.id] = true;
      for (let k in vertex.adjacencies) {
        let adjacency = vertex.adjacencies[k];
        this.traverse(marked, stack, adjacency.target);
      }
      stack.push(vertex);
    }
  }
}
