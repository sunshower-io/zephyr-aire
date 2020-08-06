import * as _ from 'lodash';
import { Traversal, Node, Graph, Vertex } from './graph';

interface Link {
  id: string;
  index: number;
  node: Node<any>;
  link: number;
  considering: boolean;
}

export class Component<T> {
  elements: Node<T>[];

  constructor(public root: Node<T>) {
    this.elements = [];
  }

  toString(): string {
    let s = `{root:${this.root}`;
    for (let e of this.elements) {
      s += `\n\t${e}`;
    }
    s += '}';
    return s;
  }

  isCycle(): boolean {
    return this.elements.length > 1;
  }
}

export class Components<T> {
  private cyclic: boolean;
  readonly components: Component<T>[];

  constructor(components: Component<T>[]) {
    this.components = [];
    for (let component of components) {
      this.add(component);
    }
  }

  add(component: Component<T>): void {
    this.cyclic = this.cyclic || component.isCycle();
    this.components.push(component);
  }

  hasCycles(): boolean {
    return this.cyclic;
  }

  getCycles(): Component<T>[] {
    return _.filter(this.components, (component: Component<T>) =>
      component.isCycle()
    );
  }

  sort(): Vertex<T>[] {
    return _.reduce(
      this.components,
      (vs: Vertex<T>[], v: Component<T>) => {
        return _.reduce(
          v.elements,
          (ves, ve) => {
            ves.unshift(ve);
            return ves;
          },
          vs
        );
      },
      []
    );
  }
}

export class TarjansStronglyConnectedComponents<T>
  implements Traversal<Components<T>, T> {
  run(g: Graph<T>): Components<T> {
    let index = 0,
      stack = [],
      results = [],
      links: { [key: string]: Link } = {};
    for (let key in g.nodes) {
      if (!links[key]) {
        index = this.compute(g, g.nodes[key], links, stack, results, index);
      }
    }
    return new Components<T>(results);
  }

  private compute(
    graph: Graph<T>,
    node: Node<T>,
    links: { [key: string]: Link },
    stack: Link[],
    results: Component<T>[],
    index: number
  ): number {
    let link = {
      id: node.id,
      node: node,
      index: index,
      link: index,
      considering: true
    };
    index++;
    stack.push(link);
    links[node.id] = link;

    let neighbors = graph.neighbors(node);
    for (let neighbor of neighbors) {
      if (!links[neighbor.id]) {
        index = this.compute(graph, neighbor, links, stack, results, index);
        link.link = Math.min(links[node.id].link, links[neighbor.id].link);
      } else {
        let nlink = links[neighbor.id];
        if (nlink.considering) {
          link.link = Math.min(link.link, nlink.index);
        }
      }
    }
    if (link.index === link.link) {
      let component = new Component<T>(node),
        current: Node<T> = null;
      do {
        current = stack.pop().node;
        component.elements.push(current);
      } while (stack.length !== 0 && node !== current);
      if (component.elements.length > 0) {
        results.push(component);
      }
    }
    return index;
  }
}
