import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { JornadaComponent } from './list/jornada.component';
import { JornadaDetailComponent } from './detail/jornada-detail.component';
import { JornadaRoutingModule } from './route/jornada-routing.module';

@NgModule({
  imports: [SharedModule, JornadaRoutingModule],
  declarations: [JornadaComponent, JornadaDetailComponent],
})
export class JornadaModule {}
