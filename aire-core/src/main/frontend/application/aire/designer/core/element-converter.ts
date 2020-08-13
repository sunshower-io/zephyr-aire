import {Element, ElementPayload, ImagePayload} from "@aire/designer/core/element";
import {Vertex}                                from "@aire/designer/core/vertex";
import {BaseImageType, Image}                  from "@aire/designer/core/image";


export class ElementConverter {

  public static convert(value : ElementPayload) : Element {

    return ElementConverter.createVertex(value);
  }


  static createVertex(value : ElementPayload) : Element {
    let result = new Vertex();
    for (let image of value.images) {
      result.addImage(ElementConverter.createImage(image));
    }
    return result;
  }

  static createImage(image : ImagePayload) : Image {
    let result = new Image();
    result.type = new BaseImageType(image.type.toLowerCase());
    result.width = image.width;
    result.height = image.height;
    result.source = image.source;
    return result;
  }
}