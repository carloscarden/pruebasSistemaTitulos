import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IJornada, Jornada } from '../jornada.model';
import { JornadaService } from '../service/jornada.service';

@Injectable({ providedIn: 'root' })
export class JornadaRoutingResolveService implements Resolve<IJornada> {
  constructor(protected service: JornadaService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IJornada> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((jornada: HttpResponse<Jornada>) => {
          if (jornada.body) {
            return of(jornada.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Jornada());
  }
}
