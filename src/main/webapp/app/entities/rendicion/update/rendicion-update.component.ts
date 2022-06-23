import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { IRendicion, Rendicion } from '../rendicion.model';
import { RendicionService } from '../service/rendicion.service';

@Component({
  selector: 'jhi-rendicion-update',
  templateUrl: './rendicion-update.component.html',
})
export class RendicionUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    dniUsuario: [],
    nombreUsuario: [],
    dniUsuarioAnulador: [],
    nombreUsuarioAnulador: [],
    motivo: [],
    causaRechazo: [],
    fechaAnulacion: [],
    dniAlumno: [],
    nombreAlumno: [],
    alumnoTituloId: [],
    establecimientoId: [],
    claveEstab: [],
    rama: [],
    nroFormulario: [],
  });

  constructor(protected rendicionService: RendicionService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ rendicion }) => {
      this.updateForm(rendicion);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const rendicion = this.createFromForm();
    if (rendicion.id !== undefined) {
      this.subscribeToSaveResponse(this.rendicionService.update(rendicion));
    } else {
      this.subscribeToSaveResponse(this.rendicionService.create(rendicion));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRendicion>>): void {
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

  protected updateForm(rendicion: IRendicion): void {
    this.editForm.patchValue({
      id: rendicion.id,
      dniUsuario: rendicion.dniUsuario,
      nombreUsuario: rendicion.nombreUsuario,
      dniUsuarioAnulador: rendicion.dniUsuarioAnulador,
      nombreUsuarioAnulador: rendicion.nombreUsuarioAnulador,
      motivo: rendicion.motivo,
      causaRechazo: rendicion.causaRechazo,
      fechaAnulacion: rendicion.fechaAnulacion,
      dniAlumno: rendicion.dniAlumno,
      nombreAlumno: rendicion.nombreAlumno,
      alumnoTituloId: rendicion.alumnoTituloId,
      establecimientoId: rendicion.establecimientoId,
      claveEstab: rendicion.claveEstab,
      rama: rendicion.rama,
      nroFormulario: rendicion.nroFormulario,
    });
  }

  protected createFromForm(): IRendicion {
    return {
      ...new Rendicion(),
      id: this.editForm.get(['id'])!.value,
      dniUsuario: this.editForm.get(['dniUsuario'])!.value,
      nombreUsuario: this.editForm.get(['nombreUsuario'])!.value,
      dniUsuarioAnulador: this.editForm.get(['dniUsuarioAnulador'])!.value,
      nombreUsuarioAnulador: this.editForm.get(['nombreUsuarioAnulador'])!.value,
      motivo: this.editForm.get(['motivo'])!.value,
      causaRechazo: this.editForm.get(['causaRechazo'])!.value,
      fechaAnulacion: this.editForm.get(['fechaAnulacion'])!.value,
      dniAlumno: this.editForm.get(['dniAlumno'])!.value,
      nombreAlumno: this.editForm.get(['nombreAlumno'])!.value,
      alumnoTituloId: this.editForm.get(['alumnoTituloId'])!.value,
      establecimientoId: this.editForm.get(['establecimientoId'])!.value,
      claveEstab: this.editForm.get(['claveEstab'])!.value,
      rama: this.editForm.get(['rama'])!.value,
      nroFormulario: this.editForm.get(['nroFormulario'])!.value,
    };
  }
}
