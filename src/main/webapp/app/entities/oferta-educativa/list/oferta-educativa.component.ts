import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IOfertaEducativa } from '../oferta-educativa.model';
import { OfertaEducativaService } from '../service/oferta-educativa.service';
import { OfertaEducativaDeleteDialogComponent } from '../delete/oferta-educativa-delete-dialog.component';

@Component({
  selector: 'jhi-oferta-educativa',
  templateUrl: './oferta-educativa.component.html',
})
export class OfertaEducativaComponent implements OnInit {
  ofertaEducativas?: IOfertaEducativa[];
  isLoading = false;

  constructor(protected ofertaEducativaService: OfertaEducativaService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.ofertaEducativaService.query().subscribe({
      next: (res: HttpResponse<IOfertaEducativa[]>) => {
        this.isLoading = false;
        this.ofertaEducativas = res.body ?? [];
      },
      error: () => {
        this.isLoading = false;
      },
    });
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(_index: number, item: IOfertaEducativa): number {
    return item.id!;
  }

  delete(ofertaEducativa: IOfertaEducativa): void {
    const modalRef = this.modalService.open(OfertaEducativaDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.ofertaEducativa = ofertaEducativa;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
