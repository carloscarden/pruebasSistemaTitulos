import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';

import { IAlumno, Alumno } from '../alumno.model';
import { AlumnoService } from '../service/alumno.service';

@Component({
  selector: 'jhi-alumno-update',
  templateUrl: './alumno-update.component.html',
})
export class AlumnoUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    documento: [],
    idTipoDocumento: [],
    nombre: [],
    apellido: [],
    fechaNacimento: [],
    sexo: [],
    ciudadNacimiento: [],
    provinciaNacimiento: [],
    idNacionalidad: [],
    idSerCreador: [],
    idProvincia: [],
  });

  constructor(protected alumnoService: AlumnoService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ alumno }) => {
      if (alumno.id === undefined) {
        const today = dayjs().startOf('day');
        alumno.fechaNacimento = today;
      }

      this.updateForm(alumno);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const alumno = this.createFromForm();
    if (alumno.id !== undefined) {
      this.subscribeToSaveResponse(this.alumnoService.update(alumno));
    } else {
      this.subscribeToSaveResponse(this.alumnoService.create(alumno));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAlumno>>): void {
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

  protected updateForm(alumno: IAlumno): void {
    this.editForm.patchValue({
      id: alumno.id,
      documento: alumno.documento,
      idTipoDocumento: alumno.idTipoDocumento,
      nombre: alumno.nombre,
      apellido: alumno.apellido,
      fechaNacimento: alumno.fechaNacimento ? alumno.fechaNacimento.format(DATE_TIME_FORMAT) : null,
      sexo: alumno.sexo,
      ciudadNacimiento: alumno.ciudadNacimiento,
      provinciaNacimiento: alumno.provinciaNacimiento,
      idNacionalidad: alumno.idNacionalidad,
      idSerCreador: alumno.idSerCreador,
      idProvincia: alumno.idProvincia,
    });
  }

  protected createFromForm(): IAlumno {
    return {
      ...new Alumno(),
      id: this.editForm.get(['id'])!.value,
      documento: this.editForm.get(['documento'])!.value,
      idTipoDocumento: this.editForm.get(['idTipoDocumento'])!.value,
      nombre: this.editForm.get(['nombre'])!.value,
      apellido: this.editForm.get(['apellido'])!.value,
      fechaNacimento: this.editForm.get(['fechaNacimento'])!.value
        ? dayjs(this.editForm.get(['fechaNacimento'])!.value, DATE_TIME_FORMAT)
        : undefined,
      sexo: this.editForm.get(['sexo'])!.value,
      ciudadNacimiento: this.editForm.get(['ciudadNacimiento'])!.value,
      provinciaNacimiento: this.editForm.get(['provinciaNacimiento'])!.value,
      idNacionalidad: this.editForm.get(['idNacionalidad'])!.value,
      idSerCreador: this.editForm.get(['idSerCreador'])!.value,
      idProvincia: this.editForm.get(['idProvincia'])!.value,
    };
  }
}
