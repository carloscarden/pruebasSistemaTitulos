import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IAlumnoTitulo } from '../alumno-titulo.model';
import { AlumnoTituloService } from '../service/alumno-titulo.service';

@Component({
  templateUrl: './alumno-titulo-delete-dialog.component.html',
})
export class AlumnoTituloDeleteDialogComponent {
  alumnoTitulo?: IAlumnoTitulo;

  constructor(protected alumnoTituloService: AlumnoTituloService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.alumnoTituloService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
