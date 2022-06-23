import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { NroSerieTituloComponent } from './list/nro-serie-titulo.component';
import { NroSerieTituloDetailComponent } from './detail/nro-serie-titulo-detail.component';
import { NroSerieTituloUpdateComponent } from './update/nro-serie-titulo-update.component';
import { NroSerieTituloDeleteDialogComponent } from './delete/nro-serie-titulo-delete-dialog.component';
import { NroSerieTituloRoutingModule } from './route/nro-serie-titulo-routing.module';

@NgModule({
  imports: [SharedModule, NroSerieTituloRoutingModule],
  declarations: [
    NroSerieTituloComponent,
    NroSerieTituloDetailComponent,
    NroSerieTituloUpdateComponent,
    NroSerieTituloDeleteDialogComponent,
  ],
  entryComponents: [NroSerieTituloDeleteDialogComponent],
})
export class NroSerieTituloModule {}
