import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';

import { IAlumnoTitulo, AlumnoTitulo } from '../alumno-titulo.model';
import { AlumnoTituloService } from '../service/alumno-titulo.service';
import { IAlumnoEstabOferta } from 'app/entities/alumno-estab-oferta/alumno-estab-oferta.model';
import { AlumnoEstabOfertaService } from 'app/entities/alumno-estab-oferta/service/alumno-estab-oferta.service';

@Component({
  selector: 'jhi-alumno-titulo-update',
  templateUrl: './alumno-titulo-update.component.html',
})
export class AlumnoTituloUpdateComponent implements OnInit {
  isSaving = false;

  alumnoEstabOfertasSharedCollection: IAlumnoEstabOferta[] = [];

  editForm = this.fb.group({
    id: [],
    idAlumnoEstabOferta: [],
    fechaEmision: [],
    nroSerie: [],
    numeroRfifd: [],
    validezNacional: [],
    fechaEnvio: [],
    fechaEgreso: [],
    nroLibroMatriz: [],
    nroActa: [],
    nroFolio: [],
    estabNombre: [],
    estabCue: [],
    estabUbicadoEn: [],
    estabCiudad: [],
    estabProvincia: [],
    idEstadoAlumnoTitulo: [],
    tituloIntermedio: [],
    promedio: [],
    fechaRuta: [],
    idRamaRuta: [],
    apYnomListoParaEnviar: [],
    documentoListoParaEnviar: [],
    apYnomEnviado: [],
    documentoEnviado: [],
    fechaUltimoEstado: [],
    alumnoEstabOferta: [],
  });

  constructor(
    protected alumnoTituloService: AlumnoTituloService,
    protected alumnoEstabOfertaService: AlumnoEstabOfertaService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ alumnoTitulo }) => {
      if (alumnoTitulo.id === undefined) {
        const today = dayjs().startOf('day');
        alumnoTitulo.fechaEmision = today;
        alumnoTitulo.fechaEnvio = today;
        alumnoTitulo.fechaEgreso = today;
        alumnoTitulo.fechaRuta = today;
        alumnoTitulo.fechaUltimoEstado = today;
      }

      this.updateForm(alumnoTitulo);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const alumnoTitulo = this.createFromForm();
    if (alumnoTitulo.id !== undefined) {
      this.subscribeToSaveResponse(this.alumnoTituloService.update(alumnoTitulo));
    } else {
      this.subscribeToSaveResponse(this.alumnoTituloService.create(alumnoTitulo));
    }
  }

  trackAlumnoEstabOfertaById(_index: number, item: IAlumnoEstabOferta): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAlumnoTitulo>>): void {
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

  protected updateForm(alumnoTitulo: IAlumnoTitulo): void {
    this.editForm.patchValue({
      id: alumnoTitulo.id,
      idAlumnoEstabOferta: alumnoTitulo.idAlumnoEstabOferta,
      fechaEmision: alumnoTitulo.fechaEmision ? alumnoTitulo.fechaEmision.format(DATE_TIME_FORMAT) : null,
      nroSerie: alumnoTitulo.nroSerie,
      numeroRfifd: alumnoTitulo.numeroRfifd,
      validezNacional: alumnoTitulo.validezNacional,
      fechaEnvio: alumnoTitulo.fechaEnvio ? alumnoTitulo.fechaEnvio.format(DATE_TIME_FORMAT) : null,
      fechaEgreso: alumnoTitulo.fechaEgreso ? alumnoTitulo.fechaEgreso.format(DATE_TIME_FORMAT) : null,
      nroLibroMatriz: alumnoTitulo.nroLibroMatriz,
      nroActa: alumnoTitulo.nroActa,
      nroFolio: alumnoTitulo.nroFolio,
      estabNombre: alumnoTitulo.estabNombre,
      estabCue: alumnoTitulo.estabCue,
      estabUbicadoEn: alumnoTitulo.estabUbicadoEn,
      estabCiudad: alumnoTitulo.estabCiudad,
      estabProvincia: alumnoTitulo.estabProvincia,
      idEstadoAlumnoTitulo: alumnoTitulo.idEstadoAlumnoTitulo,
      tituloIntermedio: alumnoTitulo.tituloIntermedio,
      promedio: alumnoTitulo.promedio,
      fechaRuta: alumnoTitulo.fechaRuta ? alumnoTitulo.fechaRuta.format(DATE_TIME_FORMAT) : null,
      idRamaRuta: alumnoTitulo.idRamaRuta,
      apYnomListoParaEnviar: alumnoTitulo.apYnomListoParaEnviar,
      documentoListoParaEnviar: alumnoTitulo.documentoListoParaEnviar,
      apYnomEnviado: alumnoTitulo.apYnomEnviado,
      documentoEnviado: alumnoTitulo.documentoEnviado,
      fechaUltimoEstado: alumnoTitulo.fechaUltimoEstado ? alumnoTitulo.fechaUltimoEstado.format(DATE_TIME_FORMAT) : null,
      alumnoEstabOferta: alumnoTitulo.alumnoEstabOferta,
    });

    this.alumnoEstabOfertasSharedCollection = this.alumnoEstabOfertaService.addAlumnoEstabOfertaToCollectionIfMissing(
      this.alumnoEstabOfertasSharedCollection,
      alumnoTitulo.alumnoEstabOferta
    );
  }

  protected loadRelationshipsOptions(): void {
    this.alumnoEstabOfertaService
      .query()
      .pipe(map((res: HttpResponse<IAlumnoEstabOferta[]>) => res.body ?? []))
      .pipe(
        map((alumnoEstabOfertas: IAlumnoEstabOferta[]) =>
          this.alumnoEstabOfertaService.addAlumnoEstabOfertaToCollectionIfMissing(
            alumnoEstabOfertas,
            this.editForm.get('alumnoEstabOferta')!.value
          )
        )
      )
      .subscribe((alumnoEstabOfertas: IAlumnoEstabOferta[]) => (this.alumnoEstabOfertasSharedCollection = alumnoEstabOfertas));
  }

  protected createFromForm(): IAlumnoTitulo {
    return {
      ...new AlumnoTitulo(),
      id: this.editForm.get(['id'])!.value,
      idAlumnoEstabOferta: this.editForm.get(['idAlumnoEstabOferta'])!.value,
      fechaEmision: this.editForm.get(['fechaEmision'])!.value
        ? dayjs(this.editForm.get(['fechaEmision'])!.value, DATE_TIME_FORMAT)
        : undefined,
      nroSerie: this.editForm.get(['nroSerie'])!.value,
      numeroRfifd: this.editForm.get(['numeroRfifd'])!.value,
      validezNacional: this.editForm.get(['validezNacional'])!.value,
      fechaEnvio: this.editForm.get(['fechaEnvio'])!.value ? dayjs(this.editForm.get(['fechaEnvio'])!.value, DATE_TIME_FORMAT) : undefined,
      fechaEgreso: this.editForm.get(['fechaEgreso'])!.value
        ? dayjs(this.editForm.get(['fechaEgreso'])!.value, DATE_TIME_FORMAT)
        : undefined,
      nroLibroMatriz: this.editForm.get(['nroLibroMatriz'])!.value,
      nroActa: this.editForm.get(['nroActa'])!.value,
      nroFolio: this.editForm.get(['nroFolio'])!.value,
      estabNombre: this.editForm.get(['estabNombre'])!.value,
      estabCue: this.editForm.get(['estabCue'])!.value,
      estabUbicadoEn: this.editForm.get(['estabUbicadoEn'])!.value,
      estabCiudad: this.editForm.get(['estabCiudad'])!.value,
      estabProvincia: this.editForm.get(['estabProvincia'])!.value,
      idEstadoAlumnoTitulo: this.editForm.get(['idEstadoAlumnoTitulo'])!.value,
      tituloIntermedio: this.editForm.get(['tituloIntermedio'])!.value,
      promedio: this.editForm.get(['promedio'])!.value,
      fechaRuta: this.editForm.get(['fechaRuta'])!.value ? dayjs(this.editForm.get(['fechaRuta'])!.value, DATE_TIME_FORMAT) : undefined,
      idRamaRuta: this.editForm.get(['idRamaRuta'])!.value,
      apYnomListoParaEnviar: this.editForm.get(['apYnomListoParaEnviar'])!.value,
      documentoListoParaEnviar: this.editForm.get(['documentoListoParaEnviar'])!.value,
      apYnomEnviado: this.editForm.get(['apYnomEnviado'])!.value,
      documentoEnviado: this.editForm.get(['documentoEnviado'])!.value,
      fechaUltimoEstado: this.editForm.get(['fechaUltimoEstado'])!.value
        ? dayjs(this.editForm.get(['fechaUltimoEstado'])!.value, DATE_TIME_FORMAT)
        : undefined,
      alumnoEstabOferta: this.editForm.get(['alumnoEstabOferta'])!.value,
    };
  }
}
