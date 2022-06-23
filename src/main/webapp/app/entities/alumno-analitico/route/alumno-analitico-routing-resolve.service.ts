import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IAlumnoAnalitico, AlumnoAnalitico } from '../alumno-analitico.model';
import { AlumnoAnaliticoService } from '../service/alumno-analitico.service';

@Injectable({ providedIn: 'root' })
export class AlumnoAnaliticoRoutingResolveService implements Resolve<IAlumnoAnalitico> {
  constructor(protected service: AlumnoAnaliticoService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAlumnoAnalitico> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((alumnoAnalitico: HttpResponse<AlumnoAnalitico>) => {
          if (alumnoAnalitico.body) {
            return of(alumnoAnalitico.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new AlumnoAnalitico());
  }
}
