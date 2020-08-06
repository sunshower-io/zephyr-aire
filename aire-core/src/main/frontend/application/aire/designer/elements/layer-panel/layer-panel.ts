import { containerless, customElement } from 'aurelia-framework';
import { DesignerManager } from 'lib/designer/core/designer-manager';
import { Layer } from 'mxgraph';
import { Identifier } from 'aire/core/identifiers';

@containerless
@customElement('layer-panel')
export class LayerPanel {
  layerPanelCollapsible: HTMLElement;

  layers: LayerNode[];

  constructor(private readonly designerManager: DesignerManager) {}

  attached() {
    this.layers = [];
    let canvas = this.designerManager.getCurrentCanvas(),
      defaultParent = canvas.getDefaultParent(),
      cells = canvas.getChildVertices(defaultParent);

    this.layers = cells.map(t => this.parseNode(t));

    this.designerManager
      .getCurrentCanvas()
      .listen('graph-changed')
      .forEach(t => {
        this.layers = [];
        let outerArray = t.data as any;
        for (let i = 0; i < outerArray.length; i++) {
          let node = this.parseNode(outerArray[i]);
          if (node != null) {
            this.layers.push(node);
          }
        }
      });
  }

  parseNode(node: Layer): LayerNode {
    if (node && node.isVertex()) {
      let layerNode = new LayerNode();
      layerNode.title = (node as any).label || (node as any).getValue();
      layerNode.icon = (node as any).image;
      layerNode.self = node;
      layerNode.id = node.id;
      layerNode.layer = node;
      if (node.children) {
        for (let j = 0; j < node.children.length; j++) {
          let childNode = this.parseNode(node.children[j]);
          if (childNode != null) {
            layerNode.children.push(childNode);
          }
        }
      }

      return layerNode;
    }
    return null;
  }

  select(event: Event) {
    let node = (event as any).detail;
    this.designerManager
      .getCurrentCanvas()
      .getSelectionModel()
      .addCell(node);
  }
}

export class LayerNode {
  title: string;
  icon: string;
  self: any;
  layer: Layer;
  children: LayerNode[] = [];
  id: string;

  constructor() {}
}
