import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { AlumnoTituloComponent } from './list/alumno-titulo.component';
import { AlumnoTituloDetailComponent } from './detail/alumno-titulo-detail.component';
import { AlumnoTituloUpdateComponent } from './update/alumno-titulo-update.component';
import { AlumnoTituloDeleteDialogComponent } from './delete/alumno-titulo-delete-dialog.component';
import { AlumnoTituloRoutingModule } from './route/alumno-titulo-routing.module';

@NgModule({
  imports: [SharedModule, AlumnoTituloRoutingModule],
  declarations: [AlumnoTituloComponent, AlumnoTituloDetailComponent, AlumnoTituloUpdateComponent, AlumnoTituloDeleteDialogComponent],
  entryComponents: [AlumnoTituloDeleteDialogComponent],
})
export class AlumnoTituloModule {}
