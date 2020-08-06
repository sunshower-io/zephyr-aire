import { LocalStorage } from './local';

import { inject } from 'aurelia-framework';

export type ViewState = LocalStorage;

export type Preferences = JSON;

@inject(LocalStorage)
export class PreferenceManager {
  private preferences: Preferences;

  private savedPreferences: any;

  constructor(private localStorage: LocalStorage) {
    this.savedPreferences = [];
  }

  preference(key: string): any {
    for (let prefs of this.savedPreferences) {
      if (prefs.region === key) {
        return prefs;
      }
    }
  }

  put(key: string, value: any): void {
    let thisPreference = this.preference(key);
    if (thisPreference) {
      thisPreference.values = value;
    } else {
      this.savedPreferences.push({ region: key, values: value });
    }
  }

  get(key: string, defaults: any): any {
    let thisPreference = this.preference(key),
      savedPrefs = thisPreference ? thisPreference.values : {},
      mergedPrefs = {};

    for (let key in defaults) {
      if (defaults.hasOwnProperty(key)) {
        mergedPrefs[key] = key in savedPrefs ? savedPrefs[key] : defaults[key];
      }
    }

    this.put(key, mergedPrefs);
    return mergedPrefs;
  }
}

export class Parameters {
  private store: any;

  constructor() {
    this.store = {};
  }

  assign(p: any) {
    Object.assign(this.store, p);
  }

  isTruthy(key: string): boolean {
    let v = this.store[key];
    return v && (v == 'true' || v == '1' || v.toLowerCase() == 'true');
  }
}

export class ApplicationState {
  private queryParameters: Parameters;

  private pathParameters: Parameters;

  constructor() {
    this.reset();
  }

  queryParams(): Parameters {
    return this.queryParameters;
  }

  pathParams(): Parameters {
    return this.pathParameters;
  }

  reset(): void {
    this.pathParameters = new Parameters();
    this.queryParameters = new Parameters();
  }

  merge(params: any, queryParams: any) {
    this.pathParameters.assign(params);
    this.queryParameters.assign(queryParams);
  }
}
