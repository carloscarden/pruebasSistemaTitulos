import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';

import { IOfertaEducativa, OfertaEducativa } from '../oferta-educativa.model';
import { OfertaEducativaService } from '../service/oferta-educativa.service';

@Component({
  selector: 'jhi-oferta-educativa-update',
  templateUrl: './oferta-educativa-update.component.html',
})
export class OfertaEducativaUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    idModalidad: [],
    idNivel: [],
    idOrientacion: [],
    idTituloDenominacion: [],
    observiaciones: [],
    idTipoTitulo: [],
    idUser: [],
    fechaInicio: [],
    fechaFin: [],
    fechaEstado: [],
    idEstadoOferta: [],
    idLeyEducacion: [],
    idNormaAprobacionDen: [],
    idNormaDictamenDen: [],
    idSeCorrespondeCon: [],
    titulo: [],
    tituloImpresion: [],
    tituloIntermedio: [],
    tituloIntermedioImpresion: [],
    orientacion: [],
    idRama: [],
    idSeccionTituloIntermedio: [],
    idCursoGrupoNombre: [],
    correlatividad: [],
    idModalidadDictado: [],
    titula: [],
    cicloBasico: [],
  });

  constructor(
    protected ofertaEducativaService: OfertaEducativaService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ofertaEducativa }) => {
      if (ofertaEducativa.id === undefined) {
        const today = dayjs().startOf('day');
        ofertaEducativa.fechaInicio = today;
        ofertaEducativa.fechaFin = today;
        ofertaEducativa.fechaEstado = today;
      }

      this.updateForm(ofertaEducativa);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const ofertaEducativa = this.createFromForm();
    if (ofertaEducativa.id !== undefined) {
      this.subscribeToSaveResponse(this.ofertaEducativaService.update(ofertaEducativa));
    } else {
      this.subscribeToSaveResponse(this.ofertaEducativaService.create(ofertaEducativa));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOfertaEducativa>>): void {
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

  protected updateForm(ofertaEducativa: IOfertaEducativa): void {
    this.editForm.patchValue({
      id: ofertaEducativa.id,
      idModalidad: ofertaEducativa.idModalidad,
      idNivel: ofertaEducativa.idNivel,
      idOrientacion: ofertaEducativa.idOrientacion,
      idTituloDenominacion: ofertaEducativa.idTituloDenominacion,
      observiaciones: ofertaEducativa.observiaciones,
      idTipoTitulo: ofertaEducativa.idTipoTitulo,
      idUser: ofertaEducativa.idUser,
      fechaInicio: ofertaEducativa.fechaInicio ? ofertaEducativa.fechaInicio.format(DATE_TIME_FORMAT) : null,
      fechaFin: ofertaEducativa.fechaFin ? ofertaEducativa.fechaFin.format(DATE_TIME_FORMAT) : null,
      fechaEstado: ofertaEducativa.fechaEstado ? ofertaEducativa.fechaEstado.format(DATE_TIME_FORMAT) : null,
      idEstadoOferta: ofertaEducativa.idEstadoOferta,
      idLeyEducacion: ofertaEducativa.idLeyEducacion,
      idNormaAprobacionDen: ofertaEducativa.idNormaAprobacionDen,
      idNormaDictamenDen: ofertaEducativa.idNormaDictamenDen,
      idSeCorrespondeCon: ofertaEducativa.idSeCorrespondeCon,
      titulo: ofertaEducativa.titulo,
      tituloImpresion: ofertaEducativa.tituloImpresion,
      tituloIntermedio: ofertaEducativa.tituloIntermedio,
      tituloIntermedioImpresion: ofertaEducativa.tituloIntermedioImpresion,
      orientacion: ofertaEducativa.orientacion,
      idRama: ofertaEducativa.idRama,
      idSeccionTituloIntermedio: ofertaEducativa.idSeccionTituloIntermedio,
      idCursoGrupoNombre: ofertaEducativa.idCursoGrupoNombre,
      correlatividad: ofertaEducativa.correlatividad,
      idModalidadDictado: ofertaEducativa.idModalidadDictado,
      titula: ofertaEducativa.titula,
      cicloBasico: ofertaEducativa.cicloBasico,
    });
  }

  protected createFromForm(): IOfertaEducativa {
    return {
      ...new OfertaEducativa(),
      id: this.editForm.get(['id'])!.value,
      idModalidad: this.editForm.get(['idModalidad'])!.value,
      idNivel: this.editForm.get(['idNivel'])!.value,
      idOrientacion: this.editForm.get(['idOrientacion'])!.value,
      idTituloDenominacion: this.editForm.get(['idTituloDenominacion'])!.value,
      observiaciones: this.editForm.get(['observiaciones'])!.value,
      idTipoTitulo: this.editForm.get(['idTipoTitulo'])!.value,
      idUser: this.editForm.get(['idUser'])!.value,
      fechaInicio: this.editForm.get(['fechaInicio'])!.value
        ? dayjs(this.editForm.get(['fechaInicio'])!.value, DATE_TIME_FORMAT)
        : undefined,
      fechaFin: this.editForm.get(['fechaFin'])!.value ? dayjs(this.editForm.get(['fechaFin'])!.value, DATE_TIME_FORMAT) : undefined,
      fechaEstado: this.editForm.get(['fechaEstado'])!.value
        ? dayjs(this.editForm.get(['fechaEstado'])!.value, DATE_TIME_FORMAT)
        : undefined,
      idEstadoOferta: this.editForm.get(['idEstadoOferta'])!.value,
      idLeyEducacion: this.editForm.get(['idLeyEducacion'])!.value,
      idNormaAprobacionDen: this.editForm.get(['idNormaAprobacionDen'])!.value,
      idNormaDictamenDen: this.editForm.get(['idNormaDictamenDen'])!.value,
      idSeCorrespondeCon: this.editForm.get(['idSeCorrespondeCon'])!.value,
      titulo: this.editForm.get(['titulo'])!.value,
      tituloImpresion: this.editForm.get(['tituloImpresion'])!.value,
      tituloIntermedio: this.editForm.get(['tituloIntermedio'])!.value,
      tituloIntermedioImpresion: this.editForm.get(['tituloIntermedioImpresion'])!.value,
      orientacion: this.editForm.get(['orientacion'])!.value,
      idRama: this.editForm.get(['idRama'])!.value,
      idSeccionTituloIntermedio: this.editForm.get(['idSeccionTituloIntermedio'])!.value,
      idCursoGrupoNombre: this.editForm.get(['idCursoGrupoNombre'])!.value,
      correlatividad: this.editForm.get(['correlatividad'])!.value,
      idModalidadDictado: this.editForm.get(['idModalidadDictado'])!.value,
      titula: this.editForm.get(['titula'])!.value,
      cicloBasico: this.editForm.get(['cicloBasico'])!.value,
    };
  }
}
