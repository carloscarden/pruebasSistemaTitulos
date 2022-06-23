import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRendicion } from '../rendicion.model';

@Component({
  selector: 'jhi-rendicion-detail',
  templateUrl: './rendicion-detail.component.html',
})
export class RendicionDetailComponent implements OnInit {
  rendicion: IRendicion | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ rendicion }) => {
      this.rendicion = rendicion;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
