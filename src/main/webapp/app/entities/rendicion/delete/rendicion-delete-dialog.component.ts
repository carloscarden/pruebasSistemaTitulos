import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IRendicion } from '../rendicion.model';
import { RendicionService } from '../service/rendicion.service';

@Component({
  templateUrl: './rendicion-delete-dialog.component.html',
})
export class RendicionDeleteDialogComponent {
  rendicion?: IRendicion;

  constructor(protected rendicionService: RendicionService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.rendicionService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
