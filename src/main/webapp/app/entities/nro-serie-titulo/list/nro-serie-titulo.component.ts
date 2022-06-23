import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { INroSerieTitulo } from '../nro-serie-titulo.model';
import { NroSerieTituloService } from '../service/nro-serie-titulo.service';
import { NroSerieTituloDeleteDialogComponent } from '../delete/nro-serie-titulo-delete-dialog.component';

@Component({
  selector: 'jhi-nro-serie-titulo',
  templateUrl: './nro-serie-titulo.component.html',
})
export class NroSerieTituloComponent implements OnInit {
  nroSerieTitulos?: INroSerieTitulo[];
  isLoading = false;

  constructor(protected nroSerieTituloService: NroSerieTituloService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.nroSerieTituloService.query().subscribe({
      next: (res: HttpResponse<INroSerieTitulo[]>) => {
        this.isLoading = false;
        this.nroSerieTitulos = res.body ?? [];
      },
      error: () => {
        this.isLoading = false;
      },
    });
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(_index: number, item: INroSerieTitulo): number {
    return item.id!;
  }

  delete(nroSerieTitulo: INroSerieTitulo): void {
    const modalRef = this.modalService.open(NroSerieTituloDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.nroSerieTitulo = nroSerieTitulo;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
