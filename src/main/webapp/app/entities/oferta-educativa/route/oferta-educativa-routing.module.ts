import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { OfertaEducativaComponent } from '../list/oferta-educativa.component';
import { OfertaEducativaDetailComponent } from '../detail/oferta-educativa-detail.component';
import { OfertaEducativaUpdateComponent } from '../update/oferta-educativa-update.component';
import { OfertaEducativaRoutingResolveService } from './oferta-educativa-routing-resolve.service';

const ofertaEducativaRoute: Routes = [
  {
    path: '',
    component: OfertaEducativaComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: OfertaEducativaDetailComponent,
    resolve: {
      ofertaEducativa: OfertaEducativaRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: OfertaEducativaUpdateComponent,
    resolve: {
      ofertaEducativa: OfertaEducativaRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: OfertaEducativaUpdateComponent,
    resolve: {
      ofertaEducativa: OfertaEducativaRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(ofertaEducativaRoute)],
  exports: [RouterModule],
})
export class OfertaEducativaRoutingModule {}
