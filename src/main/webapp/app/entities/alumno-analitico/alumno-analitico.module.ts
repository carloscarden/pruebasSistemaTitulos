import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { AlumnoAnaliticoComponent } from './list/alumno-analitico.component';
import { AlumnoAnaliticoDetailComponent } from './detail/alumno-analitico-detail.component';
import { AlumnoAnaliticoUpdateComponent } from './update/alumno-analitico-update.component';
import { AlumnoAnaliticoDeleteDialogComponent } from './delete/alumno-analitico-delete-dialog.component';
import { AlumnoAnaliticoRoutingModule } from './route/alumno-analitico-routing.module';

@NgModule({
  imports: [SharedModule, AlumnoAnaliticoRoutingModule],
  declarations: [
    AlumnoAnaliticoComponent,
    AlumnoAnaliticoDetailComponent,
    AlumnoAnaliticoUpdateComponent,
    AlumnoAnaliticoDeleteDialogComponent,
  ],
  entryComponents: [AlumnoAnaliticoDeleteDialogComponent],
})
export class AlumnoAnaliticoModule {}
