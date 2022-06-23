import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IAlumnoAnalitico } from '../alumno-analitico.model';
import { AlumnoAnaliticoService } from '../service/alumno-analitico.service';
import { AlumnoAnaliticoDeleteDialogComponent } from '../delete/alumno-analitico-delete-dialog.component';

@Component({
  selector: 'jhi-alumno-analitico',
  templateUrl: './alumno-analitico.component.html',
})
export class AlumnoAnaliticoComponent implements OnInit {
  alumnoAnaliticos?: IAlumnoAnalitico[];
  isLoading = false;

  constructor(protected alumnoAnaliticoService: AlumnoAnaliticoService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.alumnoAnaliticoService.query().subscribe({
      next: (res: HttpResponse<IAlumnoAnalitico[]>) => {
        this.isLoading = false;
        this.alumnoAnaliticos = res.body ?? [];
      },
      error: () => {
        this.isLoading = false;
      },
    });
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(_index: number, item: IAlumnoAnalitico): number {
    return item.id!;
  }

  delete(alumnoAnalitico: IAlumnoAnalitico): void {
    const modalRef = this.modalService.open(AlumnoAnaliticoDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.alumnoAnalitico = alumnoAnalitico;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
