import { Rest } from 'aurelia-api';

import { Observer, Subject, Subscription } from 'rxjs';
import { Class } from './lang';
import { read, write } from 'condensation/condensation';

export type EventType =
  | 'get'
  | 'update'
  | 'list'
  | 'remove'
  | 'create'
  | 'refresh'
  | 'loading-started'
  | 'loading-complete';

export interface RepositoryEvent<T> {
  type: EventType;
  value: any;
}

export interface Repository<ID, T> {
  count(): Promise<number>;

  get(id: ID): Promise<T>;

  update(id: ID, entity: T): Promise<ID>;

  list(): Promise<T[]>;

  remove(id: ID): Promise<T>;

  create(t: T): Promise<T>;

  getStore(): Rest;

  isLoading(): boolean;

  refresh(): void;

  subscribe(observer: Observer<RepositoryEvent<T>>): Subscription;

  // subscribe(observer: Observer<RepositoryEvent<T>>)
}

export namespace Methods {
  export const GET = 'get';
  export const PUT = 'put';
  export const DELETE = 'delete';
  export const POST = 'post';
}

export class Value<T> {
  value: T;
  type: string;
}

export interface EndpointConfiguration {
  basePath(): string;

  listUrl(): string;

  getUrl(id: any): string;
  deleteUrl(id: any): string;

  updateUrl(id: any, value: any): string;
}

export interface RefreshOperation {
  operation: () => Promise<any[]>;
}

export class BaseRepository<ID, T> implements Repository<ID, T> {
  private loading: boolean;
  readonly subject: Subject<RepositoryEvent<T>>;

  constructor(
    private readonly type: Class<T>,
    protected readonly store: Rest,
    protected readonly restCfg: EndpointConfiguration
  ) {
    this.subject = new Subject<RepositoryEvent<T>>();
  }

  isLoading(): boolean {
    return this.loading;
  }

  async count(): Promise<number> {
    this.startLoading(null);
    let url = this.restCfg.getUrl('count'),
      resp = await this.store.request(Methods.GET, url);
    this.subject.next({
      value: resp.value,
      type: 'get'
    });
    this.stopLoading(resp.value);
    return resp.value as number;
  }

  async refresh(op?: RefreshOperation): Promise<void> {
    this.subject.next({
      type: 'refresh',
      value: op
    });
  }

  async get(id: ID): Promise<T> {
    this.startLoading(id);
    let url = this.restCfg.getUrl(id),
      resp = await this.store.request(Methods.GET, url),
      result = read(this.type, resp) as T;
    this.subject.next({
      value: result,
      type: 'get'
    });
    this.stopLoading(result);
    return result;
  }

  async update(id: ID, entity: T): Promise<ID> {
    this.startLoading({
      id: id,
      entity: entity
    });

    let cfg = this.restCfg,
      url = cfg.updateUrl(id, entity),
      resp = await this.store.request(Methods.PUT, url, write(entity)),
      result = read(Value, resp) as Value<ID>;
    this.subject.next({
      value: result,
      type: 'update'
    });
    this.stopLoading(result);
    return result.value as ID;
  }

  async list(): Promise<T[]> {
    this.startLoading(null);

    let cfg = this.restCfg,
      url = cfg.listUrl(),
      resp = await this.store.request(Methods.GET, url),
      result = read(this.type, resp) as T[];
    this.subject.next({
      value: result,
      type: 'update'
    });
    this.stopLoading(result);
    return result;
  }

  async remove(id: ID): Promise<T> {
    this.startLoading(null);

    let cfg = this.restCfg,
      url = cfg.deleteUrl(id),
      resp = await this.store.request(Methods.DELETE, url),
      result = read(this.type, resp) as T[];
    this.subject.next({
      value: result,
      type: 'update'
    });
    this.stopLoading(result);
    return null;
  }

  async create(t: T): Promise<T> {
    this.startLoading(t);
    let cfg = this.restCfg,
      resp = await this.store.create(cfg.basePath(), t),
      result = read(this.type, resp) as T;
    this.stopLoading(result);
    return result as T;
  }

  getStore(): Rest {
    return this.store;
  }

  subscribe(observer: Observer<RepositoryEvent<T>>): Subscription {
    return this.subject.subscribe(observer);
  }

  protected startLoading(value: any) {
    this.loading = true;
    this.subject.next({
      value: value,
      type: 'loading-started'
    });
  }

  protected stopLoading(value: any) {
    this.loading = false;
    this.subject.next({
      value: value,
      type: 'loading-complete'
    });
  }
}
