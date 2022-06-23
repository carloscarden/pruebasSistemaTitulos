import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INroSerieTitulo } from '../nro-serie-titulo.model';

@Component({
  selector: 'jhi-nro-serie-titulo-detail',
  templateUrl: './nro-serie-titulo-detail.component.html',
})
export class NroSerieTituloDetailComponent implements OnInit {
  nroSerieTitulo: INroSerieTitulo | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ nroSerieTitulo }) => {
      this.nroSerieTitulo = nroSerieTitulo;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
