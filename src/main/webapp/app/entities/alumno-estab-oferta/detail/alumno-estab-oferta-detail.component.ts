import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAlumnoEstabOferta } from '../alumno-estab-oferta.model';

@Component({
  selector: 'jhi-alumno-estab-oferta-detail',
  templateUrl: './alumno-estab-oferta-detail.component.html',
})
export class AlumnoEstabOfertaDetailComponent implements OnInit {
  alumnoEstabOferta: IAlumnoEstabOferta | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ alumnoEstabOferta }) => {
      this.alumnoEstabOferta = alumnoEstabOferta;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
