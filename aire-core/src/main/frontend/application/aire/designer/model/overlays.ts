import { Enum }                       from '../../core/lang';
import { mxCellOverlay, mxConstants } from 'mxgraph/javascript/mxClient';
import {Image, Point}                 from "../core";

// import { Point, Image } from 'lib/designer/core';

const Loc = Enum([
  mxConstants.ALIGN_LEFT,
  mxConstants.ALIGN_CENTER,
  mxConstants.ALIGN_BOTTOM,
  mxConstants.ALIGN_MIDDLE,
  mxConstants.ALIGN_RIGHT,
  mxConstants.ALIGN_TOP
]);

export type Location = keyof typeof Loc;

export class CenterOverlay extends mxCellOverlay {
  constructor(
    image?: Image,
    tooltip?: string,
    align?: Location,
    verticalAlign?: Location,
    offset?: Point,
    cursor?: string
  ) {
    super(image, tooltip, align, verticalAlign, offset, cursor);
  }
}
