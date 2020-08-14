import {Image, ImageType} from "@aire/designer/core/image";

export enum Category {
  Palette = "palette",
  Element = "element"
}


export interface ImagePayload {
  width: number;
  height:number;
  type: string;
  source: string;

}
export interface ElementPayload {
  category : string;
  images : ImagePayload[];
}

export interface Element {
  category: Category;
  images: Map<String, Image>;

  addImage(image:Image): void;

  getImage(key: ImageType) : Image;

}