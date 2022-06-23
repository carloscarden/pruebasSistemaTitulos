import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { INroSerieTitulo } from '../nro-serie-titulo.model';
import { NroSerieTituloService } from '../service/nro-serie-titulo.service';

@Component({
  templateUrl: './nro-serie-titulo-delete-dialog.component.html',
})
export class NroSerieTituloDeleteDialogComponent {
  nroSerieTitulo?: INroSerieTitulo;

  constructor(protected nroSerieTituloService: NroSerieTituloService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.nroSerieTituloService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
