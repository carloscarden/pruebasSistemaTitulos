import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IAlumnoEstabOferta } from '../alumno-estab-oferta.model';
import { AlumnoEstabOfertaService } from '../service/alumno-estab-oferta.service';

@Component({
  templateUrl: './alumno-estab-oferta-delete-dialog.component.html',
})
export class AlumnoEstabOfertaDeleteDialogComponent {
  alumnoEstabOferta?: IAlumnoEstabOferta;

  constructor(protected alumnoEstabOfertaService: AlumnoEstabOfertaService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.alumnoEstabOfertaService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
