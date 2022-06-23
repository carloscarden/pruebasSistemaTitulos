import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { TurnoComponent } from '../list/turno.component';
import { TurnoDetailComponent } from '../detail/turno-detail.component';
import { TurnoRoutingResolveService } from './turno-routing-resolve.service';

const turnoRoute: Routes = [
  {
    path: '',
    component: TurnoComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TurnoDetailComponent,
    resolve: {
      turno: TurnoRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(turnoRoute)],
  exports: [RouterModule],
})
export class TurnoRoutingModule {}
