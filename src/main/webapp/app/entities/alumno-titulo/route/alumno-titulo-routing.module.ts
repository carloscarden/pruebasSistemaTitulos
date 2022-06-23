import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { AlumnoTituloComponent } from '../list/alumno-titulo.component';
import { AlumnoTituloDetailComponent } from '../detail/alumno-titulo-detail.component';
import { AlumnoTituloUpdateComponent } from '../update/alumno-titulo-update.component';
import { AlumnoTituloRoutingResolveService } from './alumno-titulo-routing-resolve.service';

const alumnoTituloRoute: Routes = [
  {
    path: '',
    component: AlumnoTituloComponent,
    data: {
      defaultSort: 'id,asc',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AlumnoTituloDetailComponent,
    resolve: {
      alumnoTitulo: AlumnoTituloRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AlumnoTituloUpdateComponent,
    resolve: {
      alumnoTitulo: AlumnoTituloRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AlumnoTituloUpdateComponent,
    resolve: {
      alumnoTitulo: AlumnoTituloRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(alumnoTituloRoute)],
  exports: [RouterModule],
})
export class AlumnoTituloRoutingModule {}
