import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { OfertaEducativaComponent } from './list/oferta-educativa.component';
import { OfertaEducativaDetailComponent } from './detail/oferta-educativa-detail.component';
import { OfertaEducativaUpdateComponent } from './update/oferta-educativa-update.component';
import { OfertaEducativaDeleteDialogComponent } from './delete/oferta-educativa-delete-dialog.component';
import { OfertaEducativaRoutingModule } from './route/oferta-educativa-routing.module';

@NgModule({
  imports: [SharedModule, OfertaEducativaRoutingModule],
  declarations: [
    OfertaEducativaComponent,
    OfertaEducativaDetailComponent,
    OfertaEducativaUpdateComponent,
    OfertaEducativaDeleteDialogComponent,
  ],
  entryComponents: [OfertaEducativaDeleteDialogComponent],
})
export class OfertaEducativaModule {}
