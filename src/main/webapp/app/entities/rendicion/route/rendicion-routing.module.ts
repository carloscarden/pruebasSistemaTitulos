import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { RendicionComponent } from '../list/rendicion.component';
import { RendicionDetailComponent } from '../detail/rendicion-detail.component';
import { RendicionUpdateComponent } from '../update/rendicion-update.component';
import { RendicionRoutingResolveService } from './rendicion-routing-resolve.service';

const rendicionRoute: Routes = [
  {
    path: '',
    component: RendicionComponent,
    data: {
      defaultSort: 'id,asc',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: RendicionDetailComponent,
    resolve: {
      rendicion: RendicionRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: RendicionUpdateComponent,
    resolve: {
      rendicion: RendicionRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: RendicionUpdateComponent,
    resolve: {
      rendicion: RendicionRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(rendicionRoute)],
  exports: [RouterModule],
})
export class RendicionRoutingModule {}
