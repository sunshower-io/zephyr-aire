import { inject } from 'aurelia-framework';
import { LocalStorage, Map } from 'lib/common/storage';

import { Authentication, ProtectedObject } from './user';
import { HttpClient } from 'aurelia-fetch-client';

export const HEADER = 'X-AUTH-TOKEN';

export class Token {
  public readonly value: string;
  public readonly expiration: string;

  constructor(value: any) {
    Object.assign(this, value);
  }
}

@inject(HttpClient, LocalStorage)
export class AuthenticationContextHolder {
  private static context: Authentication;

  constructor(
    private client: HttpClient,
    private storage: Map<string, string>
  ) {}

  public hasPermission(r: ProtectedObject): Promise<boolean> {
    return this.get().then(t => {
      for (let role of r.rolesAllowed) {
        for (let allowedRole of t.user.roles) {
          if (allowedRole.authority === role.authority) {
            return true;
          }
        }
      }
      return false;
    });
  }

  public clear(): void {
    AuthenticationContextHolder.context = null;
    this.storage.remove('X-AUTH-TOKEN');
  }

  public token(): string {
    if (AuthenticationContextHolder.context) {
      let ctx = AuthenticationContextHolder.context;
      if (ctx.token) {
        return ctx.token.value;
      }
    }
    return null;
  }

  public validate(token?: string): Promise<Authentication> {
    let t = token || this.token();
    if (t) {
      let tok = new Token({
        value: t,
        expiration: new Date()
      });
      return this.client
        .fetch('security/validate', {
          method: 'put',
          body: JSON.stringify(tok)
        })
        .then(response => this.get());
    } else {
      return Promise.reject('No token');
    }
  }

  public get(): Promise<Authentication> {
    if (AuthenticationContextHolder.context) {
      return Promise.resolve(AuthenticationContextHolder.context);
    } else if (this.storage.get(HEADER)) {
      return this.client
        .fetch('security/token/authenticate', {
          method: 'put',
          body: JSON.stringify(new Token({ value: this.storage.get(HEADER) }))
        })
        .then(response => response.json() as any)
        .then(data => {
          let result = new Authentication(data);
          AuthenticationContextHolder.context = result;
          return result;
        });
    }
  }

  public set(t: Authentication, remember: boolean) {
    AuthenticationContextHolder.context = t;
    if (remember) {
      this.storage.put(HEADER, this.token());
    }
  }
}
