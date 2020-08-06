export interface InlineImage {
  image : string;
  encoding : string;
}

export namespace Images {
  export function inlineUrl(image : InlineImage) : string {
    return image && image.image && `data:image/svg+xml;base64,${image.image}`;
  }

  export function imgData(image : any) : string {
    return `data:image/${format(image)},${data(image)}`;
  }

  export function data(img : any) : string {
    return img && (img.image || img.data);
  }

  export function format(img : any) : string {
    if (
      (img && img.format.toLowerCase() === "svg") ||
      img.data.startsWith("PD94b")
    ) {
      return "svg+xml;base64";
    } else {
      return "jpg;base64";
    }
  }
}
