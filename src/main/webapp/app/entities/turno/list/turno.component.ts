import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';

import { ITurno } from '../turno.model';
import { TurnoService } from '../service/turno.service';

@Component({
  selector: 'jhi-turno',
  templateUrl: './turno.component.html',
})
export class TurnoComponent implements OnInit {
  turnos?: ITurno[];
  isLoading = false;

  constructor(protected turnoService: TurnoService) {}

  loadAll(): void {
    this.isLoading = true;

    this.turnoService.query().subscribe({
      next: (res: HttpResponse<ITurno[]>) => {
        this.isLoading = false;
        this.turnos = res.body ?? [];
      },
      error: () => {
        this.isLoading = false;
      },
    });
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(_index: number, item: ITurno): number {
    return item.id!;
  }
}
