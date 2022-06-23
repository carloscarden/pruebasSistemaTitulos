import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IAlumnoAnalitico } from '../alumno-analitico.model';
import { AlumnoAnaliticoService } from '../service/alumno-analitico.service';

@Component({
  templateUrl: './alumno-analitico-delete-dialog.component.html',
})
export class AlumnoAnaliticoDeleteDialogComponent {
  alumnoAnalitico?: IAlumnoAnalitico;

  constructor(protected alumnoAnaliticoService: AlumnoAnaliticoService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.alumnoAnaliticoService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
