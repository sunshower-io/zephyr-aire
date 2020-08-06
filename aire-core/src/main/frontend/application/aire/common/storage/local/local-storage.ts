enum StorageType {
  LocalStorage,
  Cookie
}

export function createStorage(): Map<string, string> {
  if (typeof Storage == undefined) {
    return new CookieStorage();
  } else {
    return new LocalStorage();
  }
}

export interface Map<T, U> {
  get(key: T): U;

  put(key: T, u: U): U;

  remove(key: T): U;
}

export class LocalStorage implements Map<string, string> {
  constructor() {
    if (typeof Storage == undefined) {
      throw new Error(
        'This browser ' +
          'does not support web storage.  This is likely a ' +
          'defect in Sunshower.  Please use Cookie storage instead'
      );
    }
  }

  remove(key: string): string {
    let value = localStorage.getItem(key);
    localStorage.removeItem(key);
    return value;
  }

  get(key: string): string {
    return localStorage.getItem(key);
  }

  put(key: string, u: string): string {
    let value = localStorage.getItem(key);
    localStorage.setItem(key, u);
    return value;
  }
}

export class CookieStorage implements Map<string, string> {
  private items: { [index: string]: string };

  constructor() {
    this.items = this.parse({}, document.cookie);
  }

  get(key: string): string {
    return this.items[key];
  }

  put(key: string, value: string): string {
    document.cookie = `${key}=${value}`;
    this.items[key] = value;
    return key;
  }

  remove(key: string): string {
    let d = new Date(),
      expires = ';expires=' + d,
      name = key;
    d.setDate(d.getDate() - 1);
    let value = '';
    document.cookie = name + '=' + value + expires + '; path=/acc/html';
    value = this.items[name];
    delete this.items[name];
    return value;
  }

  private parse(
    values: { [index: string]: string },
    value: string
  ): { [index: string]: string } {
    if (value) {
      let cookies = value.split(';');
      cookies.forEach((c, i) => {
        let kv = c.split('='),
          k = kv[0].trim(),
          v = kv[1].trim();
        values[k] = v;
      });
    }
    return values;
  }
}
