import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { INroSerieTitulo, NroSerieTitulo } from '../nro-serie-titulo.model';
import { NroSerieTituloService } from '../service/nro-serie-titulo.service';

@Component({
  selector: 'jhi-nro-serie-titulo-update',
  templateUrl: './nro-serie-titulo-update.component.html',
})
export class NroSerieTituloUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nroSerieInicio: [],
    nroSerieFin: [],
    fechaInicio: [],
    fechaFin: [],
    usuarioAlta: [],
    usuarioModificacion: [],
    serie: [],
  });

  constructor(
    protected nroSerieTituloService: NroSerieTituloService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ nroSerieTitulo }) => {
      this.updateForm(nroSerieTitulo);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const nroSerieTitulo = this.createFromForm();
    if (nroSerieTitulo.id !== undefined) {
      this.subscribeToSaveResponse(this.nroSerieTituloService.update(nroSerieTitulo));
    } else {
      this.subscribeToSaveResponse(this.nroSerieTituloService.create(nroSerieTitulo));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<INroSerieTitulo>>): void {
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

  protected updateForm(nroSerieTitulo: INroSerieTitulo): void {
    this.editForm.patchValue({
      id: nroSerieTitulo.id,
      nroSerieInicio: nroSerieTitulo.nroSerieInicio,
      nroSerieFin: nroSerieTitulo.nroSerieFin,
      fechaInicio: nroSerieTitulo.fechaInicio,
      fechaFin: nroSerieTitulo.fechaFin,
      usuarioAlta: nroSerieTitulo.usuarioAlta,
      usuarioModificacion: nroSerieTitulo.usuarioModificacion,
      serie: nroSerieTitulo.serie,
    });
  }

  protected createFromForm(): INroSerieTitulo {
    return {
      ...new NroSerieTitulo(),
      id: this.editForm.get(['id'])!.value,
      nroSerieInicio: this.editForm.get(['nroSerieInicio'])!.value,
      nroSerieFin: this.editForm.get(['nroSerieFin'])!.value,
      fechaInicio: this.editForm.get(['fechaInicio'])!.value,
      fechaFin: this.editForm.get(['fechaFin'])!.value,
      usuarioAlta: this.editForm.get(['usuarioAlta'])!.value,
      usuarioModificacion: this.editForm.get(['usuarioModificacion'])!.value,
      serie: this.editForm.get(['serie'])!.value,
    };
  }
}
