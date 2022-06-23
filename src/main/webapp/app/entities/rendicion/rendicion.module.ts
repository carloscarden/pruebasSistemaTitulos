import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { RendicionComponent } from './list/rendicion.component';
import { RendicionDetailComponent } from './detail/rendicion-detail.component';
import { RendicionUpdateComponent } from './update/rendicion-update.component';
import { RendicionDeleteDialogComponent } from './delete/rendicion-delete-dialog.component';
import { RendicionRoutingModule } from './route/rendicion-routing.module';

@NgModule({
  imports: [SharedModule, RendicionRoutingModule],
  declarations: [RendicionComponent, RendicionDetailComponent, RendicionUpdateComponent, RendicionDeleteDialogComponent],
  entryComponents: [RendicionDeleteDialogComponent],
})
export class RendicionModule {}
