export interface Traversal<T, U> {
  run(g: Graph<U>): T;
}

type Relationship = Symbol | string | number;

export interface Edge<T> {
  source: Vertex<T>;
  target: Vertex<T>;
  relationship: Relationship;
}

export interface Vertex<T> {
  data?: T;

  id: string;

  adjacencies: { [key: string]: Edge<T> };

  addEdge(edge: Edge<T>): boolean;

  removeEdge(edge: Edge<T>): boolean;

  removeSuccessor(successor: Vertex<T>): boolean;

  addSuccessor(successor: Vertex<T>): boolean;

  addPredecessor(successor: Vertex<T>): boolean;

  removePredecessor(predecessor: Vertex<T>): boolean;

  getAdjacencies(relationship: Relationship): Vertex<T>[];

  createEdge(
    source: Vertex<T>,
    target: Vertex<T>,
    relationship?: Relationship
  ): Edge<T>;
}

export class DefaultEdge<T> implements Edge<T> {
  source: Vertex<T>;
  target: Vertex<T>;
  relationship: Relationship;
}

export class Node<T> implements Vertex<T> {
  adjacencies: { [key: string]: Edge<T> };

  constructor(public id: string, public data?: T) {
    this.adjacencies = {};
  }

  addPredecessor(successor: Vertex<T>): boolean {
    return false;
  }

  removePredecessor(predecessor: Vertex<T>): boolean {
    return false;
  }

  createEdge(source: Vertex<T>, target: Vertex<T>): Edge<T> {
    let edge = new DefaultEdge<T>();
    edge.source = this;
    edge.target = this;
    return edge;
  }

  getAdjacencies(relationship: Relationship): Node<T>[] {
    return [];
  }

  addSuccessor(successor: Vertex<T>): boolean {
    if (this.adjacencies[successor.id]) {
      return false;
    }
    this.adjacencies[successor.id] = this.createEdge(this, successor);
  }

  addEdge(edge: Edge<T>): boolean {
    if (this.adjacencies[edge.target.id]) {
      return false;
    }
    this.adjacencies[edge.target.id] = edge;
    return true;
  }

  removeSuccessor(successor: Node<T>): boolean {
    let t = this.adjacencies[successor.id];
    if (t) {
      delete this.adjacencies[successor.id];
      return true;
    }
    return false;
  }

  removeEdge(target: Edge<T>): boolean {
    return this.removeSuccessor(target.target);
  }

  toString(): string {
    let result = `${this.id} -> `;
    for (let k in this.adjacencies) {
      result += k + ',';
    }
    return result;
  }
}

export function copy<T>(
  g: Graph<T>,
  into: Graph<T>,
  over: (node: Vertex<T>) => Vertex<T>
): Graph<T> {
  let marked = {};
  for (let n in g.nodes) {
    let node = g.nodes[n];
    copyNode(node, marked, into, over);
  }
  return into;
}

function copyNode<T>(
  node: Vertex<T>,
  marked: {},
  into: Graph<T>,
  over: (node: Vertex<T>) => Vertex<T>
): void {
  if (!marked[node.id]) {
    marked[node.id] = true;
    for (let akey in node.adjacencies) {
      let adjacency = node.adjacencies[akey];
      into.connect(
        over(node),
        over(adjacency.target),
        adjacency.relationship
      );
    }
  }
}

export abstract class Graph<T> {
  public nodes: { [key: string]: Vertex<T> };

  add(node: Node<T>): boolean {
    this.check();
    this.nodes[node.id] = node;
    return true;
  }

  remove(id: string): Vertex<T> {
    let result = this.nodes[id];
    delete this.nodes[id];
    return result;
  }

  createEdge(
    source: Vertex<T>,
    target: Vertex<T>,
    relationship: Relationship
  ): Edge<T> {
    let edge = new DefaultEdge<T>();
    edge.source = source;
    edge.target = target;
    edge.relationship = relationship;
    return edge;
  }

  disconnect(s: Vertex<T>, t: Vertex<T>): boolean {
    this.check();
    let source = this.nodes[s.id] || s,
      target = this.nodes[t.id] || t;
    return source.removeSuccessor(target);
  }

  connectAll(
    s: Vertex<T>,
    targets: Vertex<T>[],
    relationship?: Relationship
  ): void {
    for (let t of targets) {
      this.connect(
        s,
        t,
        relationship
      );
    }
  }

  connect(s: Vertex<T>, t: Vertex<T>, relationship?: Relationship): boolean {
    this.check();
    let source = this.nodes[s.id] || s,
      target = this.nodes[t.id] || t;

    let edge = this.createEdge(source, target, relationship);
    this.nodes[source.id] = source;
    this.nodes[target.id] = target;
    return source.addEdge(edge);
  }

  get(id: string): Vertex<T> {
    this.check();
    return this.nodes[id];
  }

  getNodes(): Vertex<T>[] {
    let result = [];
    for (let k in this.nodes) {
      result.push(this.nodes[k]);
    }
    return result;
  }

  neighbors(source: Vertex<T>): Vertex<T>[] {
    let s = this.nodes[source.id];
    if (s) {
      let neighbors = s.adjacencies,
        results = [];
      for (let key in neighbors) {
        results.push(neighbors[key].target);
      }
      return results;
    } else {
      return [];
    }
  }

  private check() {
    if (!this.nodes) {
      this.nodes = {};
    }
  }

  toString(): string {
    let result = '';
    for (let key in this.nodes) {
      let node = this.nodes[key];
      result += '\n\t' + node.toString();
    }
    return result;
  }
}
