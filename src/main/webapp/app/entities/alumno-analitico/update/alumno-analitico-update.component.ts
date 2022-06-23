import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { IAlumnoAnalitico, AlumnoAnalitico } from '../alumno-analitico.model';
import { AlumnoAnaliticoService } from '../service/alumno-analitico.service';
import { IAlumnoEstabOferta } from 'app/entities/alumno-estab-oferta/alumno-estab-oferta.model';
import { AlumnoEstabOfertaService } from 'app/entities/alumno-estab-oferta/service/alumno-estab-oferta.service';
import { IMateria } from 'app/entities/materia/materia.model';
import { MateriaService } from 'app/entities/materia/service/materia.service';

@Component({
  selector: 'jhi-alumno-analitico-update',
  templateUrl: './alumno-analitico-update.component.html',
})
export class AlumnoAnaliticoUpdateComponent implements OnInit {
  isSaving = false;

  alumnoEstabOfertasSharedCollection: IAlumnoEstabOferta[] = [];
  materiasSharedCollection: IMateria[] = [];

  editForm = this.fb.group({
    id: [],
    nota: [],
    idEstadoAlumnoAnalitico: [],
    idAlumnoEstabOferta: [],
    idMateria: [],
    idMesImp: [],
    idAnoImp: [],
    establecimientoImp: [],
    alumnoEstabOferta: [],
    materia: [],
  });

  constructor(
    protected alumnoAnaliticoService: AlumnoAnaliticoService,
    protected alumnoEstabOfertaService: AlumnoEstabOfertaService,
    protected materiaService: MateriaService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ alumnoAnalitico }) => {
      this.updateForm(alumnoAnalitico);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const alumnoAnalitico = this.createFromForm();
    if (alumnoAnalitico.id !== undefined) {
      this.subscribeToSaveResponse(this.alumnoAnaliticoService.update(alumnoAnalitico));
    } else {
      this.subscribeToSaveResponse(this.alumnoAnaliticoService.create(alumnoAnalitico));
    }
  }

  trackAlumnoEstabOfertaById(_index: number, item: IAlumnoEstabOferta): number {
    return item.id!;
  }

  trackMateriaById(_index: number, item: IMateria): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAlumnoAnalitico>>): void {
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

  protected updateForm(alumnoAnalitico: IAlumnoAnalitico): void {
    this.editForm.patchValue({
      id: alumnoAnalitico.id,
      nota: alumnoAnalitico.nota,
      idEstadoAlumnoAnalitico: alumnoAnalitico.idEstadoAlumnoAnalitico,
      idAlumnoEstabOferta: alumnoAnalitico.idAlumnoEstabOferta,
      idMateria: alumnoAnalitico.idMateria,
      idMesImp: alumnoAnalitico.idMesImp,
      idAnoImp: alumnoAnalitico.idAnoImp,
      establecimientoImp: alumnoAnalitico.establecimientoImp,
      alumnoEstabOferta: alumnoAnalitico.alumnoEstabOferta,
      materia: alumnoAnalitico.materia,
    });

    this.alumnoEstabOfertasSharedCollection = this.alumnoEstabOfertaService.addAlumnoEstabOfertaToCollectionIfMissing(
      this.alumnoEstabOfertasSharedCollection,
      alumnoAnalitico.alumnoEstabOferta
    );
    this.materiasSharedCollection = this.materiaService.addMateriaToCollectionIfMissing(
      this.materiasSharedCollection,
      alumnoAnalitico.materia
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

    this.materiaService
      .query()
      .pipe(map((res: HttpResponse<IMateria[]>) => res.body ?? []))
      .pipe(
        map((materias: IMateria[]) => this.materiaService.addMateriaToCollectionIfMissing(materias, this.editForm.get('materia')!.value))
      )
      .subscribe((materias: IMateria[]) => (this.materiasSharedCollection = materias));
  }

  protected createFromForm(): IAlumnoAnalitico {
    return {
      ...new AlumnoAnalitico(),
      id: this.editForm.get(['id'])!.value,
      nota: this.editForm.get(['nota'])!.value,
      idEstadoAlumnoAnalitico: this.editForm.get(['idEstadoAlumnoAnalitico'])!.value,
      idAlumnoEstabOferta: this.editForm.get(['idAlumnoEstabOferta'])!.value,
      idMateria: this.editForm.get(['idMateria'])!.value,
      idMesImp: this.editForm.get(['idMesImp'])!.value,
      idAnoImp: this.editForm.get(['idAnoImp'])!.value,
      establecimientoImp: this.editForm.get(['establecimientoImp'])!.value,
      alumnoEstabOferta: this.editForm.get(['alumnoEstabOferta'])!.value,
      materia: this.editForm.get(['materia'])!.value,
    };
  }
}
