import {
  Palette,
  PaletteCoordinate,
  PaletteGroup,
  PropertySet,
  Reference
}                             from './model';
import {inject}               from 'aurelia-dependency-injection';
import {Endpoint, Rest}       from 'aurelia-api';
import {read, write}          from 'condensation/condensation';
import {
  CreateGyreRequest,
  Deployment,
  Gyre,
  JobProperties,
  Plan,
  Vortex
}                             from 'lib/tasks/task-model';
import {JsonCodec}            from 'lib/designer/codec/json-codec';
import {TaskGraph}            from 'lib/designer/model/graph/graph-element';
import {DesignerManager}      from 'lib/designer/core/designer-manager';
import {Canvas}               from 'lib/designer/canvas/canvas';
import {Content}              from 'lib/designer/model/graph/vertex';
import {AuthorizationService} from 'lib/auth/service';

import * as SSE                   from 'sse';
import {Observable}               from 'rxjs';
import {DefaultConstraintManager} from './dependencies';

@inject(Endpoint.of('stratosphere'), DesignerManager, AuthorizationService)
export class DesignerService {
  constructor(
    private endpoint : Rest,
    private manager : DesignerManager,
    private authService : AuthorizationService
  ) {
  }

  async delete(id : string) : Promise<void> {
    await this.endpoint.request('DELETE', `gyre/designer/${id}`);
  }

  async saveDeployment(
    deploymentRequest : Deployment
  ) : Promise<Observable<any>> {
    let idr = await this.endpoint.request(
      'POST',
      'gyre/designer/deployment',
      write(deploymentRequest)
      ),
      id = idr.value;

    return new Observable(o => {
      let EV = SSE as any,
        ev = EV.SSE,
        sse = new ev(`/stratosphere/sdk/gyre/designer/${id}/execute`, {
          method  : 'GET',
          headers : {
            'X-AUTH-TOKEN' : this.authService.getToken()
          }
        }),
        l = e => o.next(JSON.parse(e.data));
      sse.addEventListener('status', l);
      sse.addEventListener('message', l);
      sse.stream();
      return () => sse.close();
    });
  }

  async updateGraph(id : string) : Promise<void> {
    let canvas = this.manager.getCurrentCanvas(),
      g = new JsonCodec().export(canvas.getModel(), canvas) as any;
    g.type = 'io.sunshower.gyre.api.model.GraphElement';
    await this.endpoint.request(
      'POST',
      `gyre/designer/save/${id}/graph`,
      write(g)
    );
  }

  async saveModel(
    createJobRequest : CreateGyreRequest,
    refId : string
  ) : Promise<Gyre> {
    let canvas = this.manager.getCurrentCanvas(),
      pid = this.authService.principal.id,
      g = new JsonCodec().export(canvas.getModel(), canvas) as any;
    g.type = 'io.sunshower.gyre.api.model.GraphElement';
    createJobRequest.vortex = new Vortex({
      key       : pid,
      namespace : pid
    });
    createJobRequest.group = new Reference();
    (createJobRequest.group.key = 'Templates'),
      (createJobRequest.graph = g as TaskGraph);

    let resp = await this.endpoint.request(
      'PUT',
      `gyre/designer/${refId}/update`,
      write(createJobRequest)
    );
    return read(Gyre, resp) as Gyre;
  }

  async saveGraph(id : string) : Promise<void> {
    let canvas = this.manager.getCurrentCanvas(),
      g = new JsonCodec().export(canvas.getModel(), canvas) as any;
    g.type = 'io.sunshower.gyre.api.model.GraphElement';
    await this.endpoint.request(
      'POST',
      `gyre/designer/save/${id}/graph`,
      write(g)
    );
  }

  async getProperties(gid : string, nid : string) : Promise<PropertySet> {
    let p = await this.endpoint.request(
      'GET',
      `gyre/designer/${gid}/${nid}/properties`
      ),
      result = read(PropertySet, p) as PropertySet;
    return result;
  }

  listGroups(...coordinates : PaletteCoordinate[]) : PaletteGroup[] {
    if (!(coordinates || coordinates.length)) {
      return [];
    }
    let manager = new DefaultConstraintManager();
    this.manager.setConstraintManager(manager);
    return coordinates.map(
      t => new PaletteGroup(manager, t, this, this.manager)
    );
  }

  async list(...coordinates : PaletteCoordinate[]) : Promise<Palette[]> {
    if (!(coordinates || coordinates.length)) {
      return Promise.resolve([]);
    }
    let query = coordinates.map(t => this.toQuery(t)).join('&q='),
      result = await this.endpoint.request(
        'GET',
        `gyre/designer/palettes?q=${query}`
      );
    return read(Palette, result);
  }

  async planTask(id : string) : Promise<Plan> {
    let resp = await this.endpoint.request('GET', `gyre/designer/${id}/plan`);
    return read(Plan, resp) as Plan;
  }

  async plan(canvas : Canvas, props : JobProperties) : Promise<Plan> {
    let req = this.exportToCreateRequest(canvas, 'compute', 'gyre', props),
      result = await this.endpoint.request(
        'PUT',
        'gyre/designer/plan',
        write(req)
      );
    return read(Plan, result) as Plan;
  }

  exportToCreateRequest(
    canvas : Canvas,
    vkey : string,
    vns : string,
    props : JobProperties
  ) : CreateGyreRequest {
    let g = new JsonCodec().export(canvas.getModel(), canvas) as any,
      req = new CreateGyreRequest();
    g.type = 'io.sunshower.gyre.api.model.GraphElement';
    req.vortex = new Vortex({
      key       : vkey,
      namespace : vns
    });
    // req.referenceId = props.referenceId;
    req.name = props.name;
    req.description = props.description;
    req.graph = g as TaskGraph;
    return req;
  }

  toQuery(coordinate : PaletteCoordinate) {
    let querySegments = [];
    if (coordinate.namespace) {
      querySegments.push(`vortices.namespace==${coordinate.namespace}`);
    }
    if (coordinate.key) {
      querySegments.push(`vortices.key==${coordinate.key}`);
    }
    if (coordinate.group) {
      querySegments.push(`name==${coordinate.group}`);
    }
    if (coordinate.name) {
      querySegments.push(`tasks.name==${coordinate.name}`);
    }
    return querySegments.join(';');
  }

  async create() : Promise<string> {
    let id = await this.endpoint.request('GET', 'gyre/designer/new');
    return id.value;
  }

  async get(id : string) : Promise<Gyre> {
    let v = await this.endpoint.request('GET', `gyre/designer/${id}`),
      result = read(Gyre, v) as Gyre;
    return result;
  }

  async setContent(gid : string, nid : string, content : string) : Promise<void> {
    let c = new Content();
    c.value = content;
    await this.endpoint.request(
      'PUT',
      `gyre/designer/${gid}/${nid}/content`,
      write(c)
    );
  }

  async getContent(gid : string, nid : string) : Promise<string> {
    try {
      let e = await this.endpoint.request(
        'GET',
        `gyre/designer/${gid}/${nid}/content`
        ),
        result = read(Content, e) as Content;
      return result.value;
    } catch (e) {
      return '';
    }
  }
}
