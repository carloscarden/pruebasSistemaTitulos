import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOfertaEducativa } from '../oferta-educativa.model';

@Component({
  selector: 'jhi-oferta-educativa-detail',
  templateUrl: './oferta-educativa-detail.component.html',
})
export class OfertaEducativaDetailComponent implements OnInit {
  ofertaEducativa: IOfertaEducativa | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ofertaEducativa }) => {
      this.ofertaEducativa = ofertaEducativa;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
