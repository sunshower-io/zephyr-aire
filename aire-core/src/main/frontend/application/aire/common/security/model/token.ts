export const HEADER = 'X-AUTH-TOKEN';

export class Token {
  public readonly value : string;
  public readonly expiration : string;

  constructor(value : any) {
    Object.assign(this, value);
  }
}

