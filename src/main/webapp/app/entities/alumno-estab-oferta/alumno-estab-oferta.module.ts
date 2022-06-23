import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { AlumnoEstabOfertaComponent } from './list/alumno-estab-oferta.component';
import { AlumnoEstabOfertaDetailComponent } from './detail/alumno-estab-oferta-detail.component';
import { AlumnoEstabOfertaUpdateComponent } from './update/alumno-estab-oferta-update.component';
import { AlumnoEstabOfertaDeleteDialogComponent } from './delete/alumno-estab-oferta-delete-dialog.component';
import { AlumnoEstabOfertaRoutingModule } from './route/alumno-estab-oferta-routing.module';

@NgModule({
  imports: [SharedModule, AlumnoEstabOfertaRoutingModule],
  declarations: [
    AlumnoEstabOfertaComponent,
    AlumnoEstabOfertaDetailComponent,
    AlumnoEstabOfertaUpdateComponent,
    AlumnoEstabOfertaDeleteDialogComponent,
  ],
  entryComponents: [AlumnoEstabOfertaDeleteDialogComponent],
})
export class AlumnoEstabOfertaModule {}
