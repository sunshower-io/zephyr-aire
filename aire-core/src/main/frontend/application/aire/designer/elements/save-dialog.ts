import { UIDialog } from 'aurelia-ui-framework';
import { I18N } from 'aurelia-i18n';
import { autoinject, bindable } from 'aurelia-framework';
import { TaskService } from 'lib/tasks/task-service';
import { CreateGyreRequest, Image } from 'lib/tasks/task-model';
import { ModelAware } from './model-aware';

@autoinject
export class SaveDialog extends UIDialog {
  @bindable
  iconFile: File;

  private inlineIcon: string;

  private fileInput: any;

  private host: ModelAware;

  private createJobRequest: CreateGyreRequest;

  constructor(private i18n: I18N, private taskService: TaskService) {
    super();
  }

  iconFileChanged(nv: File) {
    let reader = new FileReader();
    reader.addEventListener('load', () => {
      this.inlineIcon = reader.result as string;
      let image = new Image();
      image.format = 'png';
      image.encoding = 'base64';
      image.data = this.inlineIcon.split(',')[1];
      this.createJobRequest.image = image;
    });

    let files = this.fileInput.inputEl.files;

    if (files && files.length > 0) {
      reader.readAsDataURL(files.item(0));
    }
  }

  canActivate(model: any) {
    this.modal = model.modal;
    this.host = model.host;
    this.title = this.i18n.tr(model.title);
  }

  deactivate(): void {}

  close() {
    this.createJobRequest.type = CreateGyreRequest.type;
    this.host.saveModel(this.createJobRequest);
    super.close();
  }
}
