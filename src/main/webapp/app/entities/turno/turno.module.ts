import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { TurnoComponent } from './list/turno.component';
import { TurnoDetailComponent } from './detail/turno-detail.component';
import { TurnoRoutingModule } from './route/turno-routing.module';

@NgModule({
  imports: [SharedModule, TurnoRoutingModule],
  declarations: [TurnoComponent, TurnoDetailComponent],
})
export class TurnoModule {}
