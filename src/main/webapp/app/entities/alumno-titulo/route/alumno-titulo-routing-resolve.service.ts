import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IAlumnoTitulo, AlumnoTitulo } from '../alumno-titulo.model';
import { AlumnoTituloService } from '../service/alumno-titulo.service';

@Injectable({ providedIn: 'root' })
export class AlumnoTituloRoutingResolveService implements Resolve<IAlumnoTitulo> {
  constructor(protected service: AlumnoTituloService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAlumnoTitulo> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((alumnoTitulo: HttpResponse<AlumnoTitulo>) => {
          if (alumnoTitulo.body) {
            return of(alumnoTitulo.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new AlumnoTitulo());
  }
}
