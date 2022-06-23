import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { INroSerieTitulo, NroSerieTitulo } from '../nro-serie-titulo.model';
import { NroSerieTituloService } from '../service/nro-serie-titulo.service';

@Injectable({ providedIn: 'root' })
export class NroSerieTituloRoutingResolveService implements Resolve<INroSerieTitulo> {
  constructor(protected service: NroSerieTituloService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<INroSerieTitulo> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((nroSerieTitulo: HttpResponse<NroSerieTitulo>) => {
          if (nroSerieTitulo.body) {
            return of(nroSerieTitulo.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new NroSerieTitulo());
  }
}
