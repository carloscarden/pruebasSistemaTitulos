import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAlumnoAnalitico } from '../alumno-analitico.model';

@Component({
  selector: 'jhi-alumno-analitico-detail',
  templateUrl: './alumno-analitico-detail.component.html',
})
export class AlumnoAnaliticoDetailComponent implements OnInit {
  alumnoAnalitico: IAlumnoAnalitico | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ alumnoAnalitico }) => {
      this.alumnoAnalitico = alumnoAnalitico;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
