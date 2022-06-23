import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IJornada } from '../jornada.model';

@Component({
  selector: 'jhi-jornada-detail',
  templateUrl: './jornada-detail.component.html',
})
export class JornadaDetailComponent implements OnInit {
  jornada: IJornada | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ jornada }) => {
      this.jornada = jornada;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
