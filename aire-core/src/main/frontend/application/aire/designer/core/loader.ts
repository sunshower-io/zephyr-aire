import { UUID } from 'aire/core/uuid';

export class DesignerLoader {
  private childElementId: string = UUID.random();

  constructor(private parentElement: HTMLElement) {}

  setLoading(): void {
    let loaderHTML =
      "<div class='fullscreen loader' id='" +
      this.childElementId +
      "'><div class='progress'><div class='indeterminate'></div></div><span>Loading ...</span></div>";
    // $(this.parentElement).append(loaderHTML);
  }

  removeLoading(): void {
    // $("#" + this.childElementId).remove();
  }
}
