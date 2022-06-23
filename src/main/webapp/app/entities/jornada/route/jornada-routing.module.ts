import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { JornadaComponent } from '../list/jornada.component';
import { JornadaDetailComponent } from '../detail/jornada-detail.component';
import { JornadaRoutingResolveService } from './jornada-routing-resolve.service';

const jornadaRoute: Routes = [
  {
    path: '',
    component: JornadaComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: JornadaDetailComponent,
    resolve: {
      jornada: JornadaRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(jornadaRoute)],
  exports: [RouterModule],
})
export class JornadaRoutingModule {}
