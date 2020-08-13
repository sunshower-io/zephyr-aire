import {Category, Element} from "@aire/designer/core/element";
import {Image, ImageType}  from "@aire/designer/core/image";

export class AbstractElement implements Element {
  category : Category;
  images : Map<string, Image>;

  public addImage(image : Image) {
    this.images = this.images || new Map<string, Image>();
    this.images.set(image.type.getKey(), image);
  }

  getImage(key : ImageType) : Image {
    return this.images.get(key.getKey());
  }
}

