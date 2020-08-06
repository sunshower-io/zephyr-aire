import { bindable, customElement, containerless } from 'aurelia-framework';
import { Router } from 'aurelia-router';

@containerless
@customElement('action-button')
export class DesignerActionButton {
  @bindable
  active: boolean = true;

  constructor(private router: Router) {}

  deploy(): void {
    // this.dialogService.open({
    //     viewModel: DeploymentPropertyPanel,
    //     model: this.router
    // }).then(t => {
    //     this.router.navigate('deployment');
    //     return t.closeResult;
    // });
  }
}
