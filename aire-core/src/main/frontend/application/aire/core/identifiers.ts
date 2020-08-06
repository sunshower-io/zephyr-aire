import { UUID } from './uuid';
import { Base58 } from './base58';
import { HttpClient } from 'aurelia-fetch-client';

export class Identifier {
  constructor(public readonly id: string) {}
}
export namespace Identifier {
  export function isIdentifier(key: string): boolean {
    return key && /[1-9a-zA-Z]+/.test(key);
  }

  export function newId(): string {
    return Base58.encode(UUID.randomBytes());
  }
}

export class Identifiers {
  private static client: HttpClient;

  public static initialize(client: HttpClient): void {
    Identifiers.client = client;
  }

  public static create(): Promise<any> {
    return Promise.resolve(Identifier.newId());
    // return Identifiers.client.fetch('identifiers')
    //     .then(t => t.json())
    //     .then(t => t);
  }
}
