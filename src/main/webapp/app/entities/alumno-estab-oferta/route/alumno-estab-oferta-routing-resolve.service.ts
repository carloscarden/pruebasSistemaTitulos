import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IAlumnoEstabOferta, AlumnoEstabOferta } from '../alumno-estab-oferta.model';
import { AlumnoEstabOfertaService } from '../service/alumno-estab-oferta.service';

@Injectable({ providedIn: 'root' })
export class AlumnoEstabOfertaRoutingResolveService implements Resolve<IAlumnoEstabOferta> {
  constructor(protected service: AlumnoEstabOfertaService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAlumnoEstabOferta> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((alumnoEstabOferta: HttpResponse<AlumnoEstabOferta>) => {
          if (alumnoEstabOferta.body) {
            return of(alumnoEstabOferta.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new AlumnoEstabOferta());
  }
}
