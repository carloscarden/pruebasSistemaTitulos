import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IOfertaEducativa } from '../oferta-educativa.model';
import { OfertaEducativaService } from '../service/oferta-educativa.service';

@Component({
  templateUrl: './oferta-educativa-delete-dialog.component.html',
})
export class OfertaEducativaDeleteDialogComponent {
  ofertaEducativa?: IOfertaEducativa;

  constructor(protected ofertaEducativaService: OfertaEducativaService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ofertaEducativaService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
