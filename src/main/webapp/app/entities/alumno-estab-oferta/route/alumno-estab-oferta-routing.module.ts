import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { AlumnoEstabOfertaComponent } from '../list/alumno-estab-oferta.component';
import { AlumnoEstabOfertaDetailComponent } from '../detail/alumno-estab-oferta-detail.component';
import { AlumnoEstabOfertaUpdateComponent } from '../update/alumno-estab-oferta-update.component';
import { AlumnoEstabOfertaRoutingResolveService } from './alumno-estab-oferta-routing-resolve.service';

const alumnoEstabOfertaRoute: Routes = [
  {
    path: '',
    component: AlumnoEstabOfertaComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AlumnoEstabOfertaDetailComponent,
    resolve: {
      alumnoEstabOferta: AlumnoEstabOfertaRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AlumnoEstabOfertaUpdateComponent,
    resolve: {
      alumnoEstabOferta: AlumnoEstabOfertaRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AlumnoEstabOfertaUpdateComponent,
    resolve: {
      alumnoEstabOferta: AlumnoEstabOfertaRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(alumnoEstabOfertaRoute)],
  exports: [RouterModule],
})
export class AlumnoEstabOfertaRoutingModule {}
