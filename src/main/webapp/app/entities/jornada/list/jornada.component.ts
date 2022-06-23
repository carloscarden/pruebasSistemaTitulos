import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';

import { IJornada } from '../jornada.model';
import { JornadaService } from '../service/jornada.service';

@Component({
  selector: 'jhi-jornada',
  templateUrl: './jornada.component.html',
})
export class JornadaComponent implements OnInit {
  jornadas?: IJornada[];
  isLoading = false;

  constructor(protected jornadaService: JornadaService) {}

  loadAll(): void {
    this.isLoading = true;

    this.jornadaService.query().subscribe({
      next: (res: HttpResponse<IJornada[]>) => {
        this.isLoading = false;
        this.jornadas = res.body ?? [];
      },
      error: () => {
        this.isLoading = false;
      },
    });
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(_index: number, item: IJornada): number {
    return item.id!;
  }
}
