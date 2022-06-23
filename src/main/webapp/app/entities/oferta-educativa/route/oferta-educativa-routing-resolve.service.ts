import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IOfertaEducativa, OfertaEducativa } from '../oferta-educativa.model';
import { OfertaEducativaService } from '../service/oferta-educativa.service';

@Injectable({ providedIn: 'root' })
export class OfertaEducativaRoutingResolveService implements Resolve<IOfertaEducativa> {
  constructor(protected service: OfertaEducativaService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IOfertaEducativa> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((ofertaEducativa: HttpResponse<OfertaEducativa>) => {
          if (ofertaEducativa.body) {
            return of(ofertaEducativa.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new OfertaEducativa());
  }
}
