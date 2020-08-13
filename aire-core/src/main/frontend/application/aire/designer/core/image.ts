/**
 * type of image (handlers will know how to deal with this)
 */
export interface ImageType {
  getKey() : string;
}


/**
 * actual image class
 */
export class Image {
  width : number;
  height : number;
  type : ImageType;
  source : string;
}


/**
 * internal members
 */
export class BaseImageType implements ImageType {
  constructor(private readonly key : string) {

  }

  getKey() : string {
    return this.key;
  }
}


export class ImageTypes {
  public static readonly Palette : ImageType = new BaseImageType("palette");
  public static readonly Element : ImageType = new BaseImageType("element");
}
