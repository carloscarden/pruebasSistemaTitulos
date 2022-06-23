import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';

import { IAlumnoEstabOferta, AlumnoEstabOferta } from '../alumno-estab-oferta.model';
import { AlumnoEstabOfertaService } from '../service/alumno-estab-oferta.service';
import { IAlumno } from 'app/entities/alumno/alumno.model';
import { AlumnoService } from 'app/entities/alumno/service/alumno.service';
import { IOfertaEducativa } from 'app/entities/oferta-educativa/oferta-educativa.model';
import { OfertaEducativaService } from 'app/entities/oferta-educativa/service/oferta-educativa.service';

@Component({
  selector: 'jhi-alumno-estab-oferta-update',
  templateUrl: './alumno-estab-oferta-update.component.html',
})
export class AlumnoEstabOfertaUpdateComponent implements OnInit {
  isSaving = false;

  alumnosSharedCollection: IAlumno[] = [];
  ofertaEducativasSharedCollection: IOfertaEducativa[] = [];

  editForm = this.fb.group({
    id: [],
    idSer: [],
    idOfertaEducativa: [],
    idAlumno: [],
    idEstadoAlumnoEstabOferta: [],
    fechaInicio: [],
    fechaFin: [],
    alumno: [],
    ofertaEducativa: [],
  });

  constructor(
    protected alumnoEstabOfertaService: AlumnoEstabOfertaService,
    protected alumnoService: AlumnoService,
    protected ofertaEducativaService: OfertaEducativaService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ alumnoEstabOferta }) => {
      if (alumnoEstabOferta.id === undefined) {
        const today = dayjs().startOf('day');
        alumnoEstabOferta.fechaInicio = today;
        alumnoEstabOferta.fechaFin = today;
      }

      this.updateForm(alumnoEstabOferta);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const alumnoEstabOferta = this.createFromForm();
    if (alumnoEstabOferta.id !== undefined) {
      this.subscribeToSaveResponse(this.alumnoEstabOfertaService.update(alumnoEstabOferta));
    } else {
      this.subscribeToSaveResponse(this.alumnoEstabOfertaService.create(alumnoEstabOferta));
    }
  }

  trackAlumnoById(_index: number, item: IAlumno): number {
    return item.id!;
  }

  trackOfertaEducativaById(_index: number, item: IOfertaEducativa): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAlumnoEstabOferta>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(alumnoEstabOferta: IAlumnoEstabOferta): void {
    this.editForm.patchValue({
      id: alumnoEstabOferta.id,
      idSer: alumnoEstabOferta.idSer,
      idOfertaEducativa: alumnoEstabOferta.idOfertaEducativa,
      idAlumno: alumnoEstabOferta.idAlumno,
      idEstadoAlumnoEstabOferta: alumnoEstabOferta.idEstadoAlumnoEstabOferta,
      fechaInicio: alumnoEstabOferta.fechaInicio ? alumnoEstabOferta.fechaInicio.format(DATE_TIME_FORMAT) : null,
      fechaFin: alumnoEstabOferta.fechaFin ? alumnoEstabOferta.fechaFin.format(DATE_TIME_FORMAT) : null,
      alumno: alumnoEstabOferta.alumno,
      ofertaEducativa: alumnoEstabOferta.ofertaEducativa,
    });

    this.alumnosSharedCollection = this.alumnoService.addAlumnoToCollectionIfMissing(
      this.alumnosSharedCollection,
      alumnoEstabOferta.alumno
    );
    this.ofertaEducativasSharedCollection = this.ofertaEducativaService.addOfertaEducativaToCollectionIfMissing(
      this.ofertaEducativasSharedCollection,
      alumnoEstabOferta.ofertaEducativa
    );
  }

  protected loadRelationshipsOptions(): void {
    this.alumnoService
      .query()
      .pipe(map((res: HttpResponse<IAlumno[]>) => res.body ?? []))
      .pipe(map((alumnos: IAlumno[]) => this.alumnoService.addAlumnoToCollectionIfMissing(alumnos, this.editForm.get('alumno')!.value)))
      .subscribe((alumnos: IAlumno[]) => (this.alumnosSharedCollection = alumnos));

    this.ofertaEducativaService
      .query()
      .pipe(map((res: HttpResponse<IOfertaEducativa[]>) => res.body ?? []))
      .pipe(
        map((ofertaEducativas: IOfertaEducativa[]) =>
          this.ofertaEducativaService.addOfertaEducativaToCollectionIfMissing(ofertaEducativas, this.editForm.get('ofertaEducativa')!.value)
        )
      )
      .subscribe((ofertaEducativas: IOfertaEducativa[]) => (this.ofertaEducativasSharedCollection = ofertaEducativas));
  }

  protected createFromForm(): IAlumnoEstabOferta {
    return {
      ...new AlumnoEstabOferta(),
      id: this.editForm.get(['id'])!.value,
      idSer: this.editForm.get(['idSer'])!.value,
      idOfertaEducativa: this.editForm.get(['idOfertaEducativa'])!.value,
      idAlumno: this.editForm.get(['idAlumno'])!.value,
      idEstadoAlumnoEstabOferta: this.editForm.get(['idEstadoAlumnoEstabOferta'])!.value,
      fechaInicio: this.editForm.get(['fechaInicio'])!.value
        ? dayjs(this.editForm.get(['fechaInicio'])!.value, DATE_TIME_FORMAT)
        : undefined,
      fechaFin: this.editForm.get(['fechaFin'])!.value ? dayjs(this.editForm.get(['fechaFin'])!.value, DATE_TIME_FORMAT) : undefined,
      alumno: this.editForm.get(['alumno'])!.value,
      ofertaEducativa: this.editForm.get(['ofertaEducativa'])!.value,
    };
  }
}
