import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { AlumnoAnaliticoComponent } from '../list/alumno-analitico.component';
import { AlumnoAnaliticoDetailComponent } from '../detail/alumno-analitico-detail.component';
import { AlumnoAnaliticoUpdateComponent } from '../update/alumno-analitico-update.component';
import { AlumnoAnaliticoRoutingResolveService } from './alumno-analitico-routing-resolve.service';

const alumnoAnaliticoRoute: Routes = [
  {
    path: '',
    component: AlumnoAnaliticoComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AlumnoAnaliticoDetailComponent,
    resolve: {
      alumnoAnalitico: AlumnoAnaliticoRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AlumnoAnaliticoUpdateComponent,
    resolve: {
      alumnoAnalitico: AlumnoAnaliticoRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AlumnoAnaliticoUpdateComponent,
    resolve: {
      alumnoAnalitico: AlumnoAnaliticoRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(alumnoAnaliticoRoute)],
  exports: [RouterModule],
})
export class AlumnoAnaliticoRoutingModule {}
