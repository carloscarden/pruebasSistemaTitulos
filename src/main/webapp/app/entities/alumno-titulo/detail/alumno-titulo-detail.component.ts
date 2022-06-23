import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAlumnoTitulo } from '../alumno-titulo.model';

@Component({
  selector: 'jhi-alumno-titulo-detail',
  templateUrl: './alumno-titulo-detail.component.html',
})
export class AlumnoTituloDetailComponent implements OnInit {
  alumnoTitulo: IAlumnoTitulo | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ alumnoTitulo }) => {
      this.alumnoTitulo = alumnoTitulo;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
