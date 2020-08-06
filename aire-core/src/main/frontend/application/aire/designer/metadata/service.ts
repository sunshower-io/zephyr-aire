import { inject } from 'aurelia-dependency-injection';
import { Endpoint, Rest } from 'aurelia-api';
import { DesignerManager } from 'lib/designer/core/designer-manager';
import { TaskMetadata } from './model';
import { read, write } from 'condensation/condensation';

@inject(Endpoint.of('stratosphere'), DesignerManager)
export class MetadataService {
  constructor(private endpoint: Rest, private manager: DesignerManager) {}

  async getMetadataOnElement(
    gid: string,
    taskId: string
  ): Promise<TaskMetadata> {
    try {
      let resp = await this.endpoint.request(
          'GET',
          `gyre/metadata/${gid}/${taskId}`
        ),
        metadata = read(TaskMetadata, resp) as TaskMetadata;
      return metadata;
    } catch (e) {
      if (e.status === 404) {
        return null;
      } else {
        throw e;
      }
    }
  }

  async getMetadata(taskId: string): Promise<TaskMetadata> {
    try {
      let resp = await this.endpoint.request('GET', `gyre/metadata/${taskId}`),
        metadata = read(TaskMetadata, resp) as TaskMetadata;
      return metadata;
    } catch (e) {
      if (e.status === 404) {
        return null;
      } else {
        throw e;
      }
    }
  }

  async saveMetadata(
    gyreId: string,
    elementId: string,
    metadata: TaskMetadata
  ): Promise<void> {
    metadata.id = elementId;
    try {
      let resp = await this.endpoint.request(
        'PUT',
        `gyre/metadata/${gyreId}`,
        write(metadata)
      );
    } catch (e) {
      if (e.status === 404) {
        return null;
      } else {
        throw e;
      }
    }
  }
}
