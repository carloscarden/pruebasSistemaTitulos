import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { NroSerieTituloComponent } from '../list/nro-serie-titulo.component';
import { NroSerieTituloDetailComponent } from '../detail/nro-serie-titulo-detail.component';
import { NroSerieTituloUpdateComponent } from '../update/nro-serie-titulo-update.component';
import { NroSerieTituloRoutingResolveService } from './nro-serie-titulo-routing-resolve.service';

const nroSerieTituloRoute: Routes = [
  {
    path: '',
    component: NroSerieTituloComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: NroSerieTituloDetailComponent,
    resolve: {
      nroSerieTitulo: NroSerieTituloRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: NroSerieTituloUpdateComponent,
    resolve: {
      nroSerieTitulo: NroSerieTituloRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: NroSerieTituloUpdateComponent,
    resolve: {
      nroSerieTitulo: NroSerieTituloRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(nroSerieTituloRoute)],
  exports: [RouterModule],
})
export class NroSerieTituloRoutingModule {}
