import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IAlumnoEstabOferta } from '../alumno-estab-oferta.model';
import { AlumnoEstabOfertaService } from '../service/alumno-estab-oferta.service';
import { AlumnoEstabOfertaDeleteDialogComponent } from '../delete/alumno-estab-oferta-delete-dialog.component';

@Component({
  selector: 'jhi-alumno-estab-oferta',
  templateUrl: './alumno-estab-oferta.component.html',
})
export class AlumnoEstabOfertaComponent implements OnInit {
  alumnoEstabOfertas?: IAlumnoEstabOferta[];
  isLoading = false;

  constructor(protected alumnoEstabOfertaService: AlumnoEstabOfertaService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.alumnoEstabOfertaService.query().subscribe({
      next: (res: HttpResponse<IAlumnoEstabOferta[]>) => {
        this.isLoading = false;
        this.alumnoEstabOfertas = res.body ?? [];
      },
      error: () => {
        this.isLoading = false;
      },
    });
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(_index: number, item: IAlumnoEstabOferta): number {
    return item.id!;
  }

  delete(alumnoEstabOferta: IAlumnoEstabOferta): void {
    const modalRef = this.modalService.open(AlumnoEstabOfertaDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.alumnoEstabOferta = alumnoEstabOferta;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
