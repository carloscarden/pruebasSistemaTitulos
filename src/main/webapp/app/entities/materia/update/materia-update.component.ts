import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { IMateria, Materia } from '../materia.model';
import { MateriaService } from '../service/materia.service';

@Component({
  selector: 'jhi-materia-update',
  templateUrl: './materia-update.component.html',
})
export class MateriaUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    idMateriaDenominacion: [],
    marcaModulo: [],
    cargaHoraria: [],
    idOfertaEducativa: [],
    idSeccion: [],
    idTipoMateriaOpcional: [],
    orden: [],
    idArea: [],
    idAsignatira: [],
    codigosChequeados: [],
    obligatoriedad: [],
    unidadCargaPedagogica: [],
  });

  constructor(protected materiaService: MateriaService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ materia }) => {
      this.updateForm(materia);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const materia = this.createFromForm();
    if (materia.id !== undefined) {
      this.subscribeToSaveResponse(this.materiaService.update(materia));
    } else {
      this.subscribeToSaveResponse(this.materiaService.create(materia));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMateria>>): void {
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

  protected updateForm(materia: IMateria): void {
    this.editForm.patchValue({
      id: materia.id,
      idMateriaDenominacion: materia.idMateriaDenominacion,
      marcaModulo: materia.marcaModulo,
      cargaHoraria: materia.cargaHoraria,
      idOfertaEducativa: materia.idOfertaEducativa,
      idSeccion: materia.idSeccion,
      idTipoMateriaOpcional: materia.idTipoMateriaOpcional,
      orden: materia.orden,
      idArea: materia.idArea,
      idAsignatira: materia.idAsignatira,
      codigosChequeados: materia.codigosChequeados,
      obligatoriedad: materia.obligatoriedad,
      unidadCargaPedagogica: materia.unidadCargaPedagogica,
    });
  }

  protected createFromForm(): IMateria {
    return {
      ...new Materia(),
      id: this.editForm.get(['id'])!.value,
      idMateriaDenominacion: this.editForm.get(['idMateriaDenominacion'])!.value,
      marcaModulo: this.editForm.get(['marcaModulo'])!.value,
      cargaHoraria: this.editForm.get(['cargaHoraria'])!.value,
      idOfertaEducativa: this.editForm.get(['idOfertaEducativa'])!.value,
      idSeccion: this.editForm.get(['idSeccion'])!.value,
      idTipoMateriaOpcional: this.editForm.get(['idTipoMateriaOpcional'])!.value,
      orden: this.editForm.get(['orden'])!.value,
      idArea: this.editForm.get(['idArea'])!.value,
      idAsignatira: this.editForm.get(['idAsignatira'])!.value,
      codigosChequeados: this.editForm.get(['codigosChequeados'])!.value,
      obligatoriedad: this.editForm.get(['obligatoriedad'])!.value,
      unidadCargaPedagogica: this.editForm.get(['unidadCargaPedagogica'])!.value,
    };
  }
}
