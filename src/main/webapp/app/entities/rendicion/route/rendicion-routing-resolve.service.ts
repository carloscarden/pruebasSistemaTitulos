import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IRendicion, Rendicion } from '../rendicion.model';
import { RendicionService } from '../service/rendicion.service';

@Injectable({ providedIn: 'root' })
export class RendicionRoutingResolveService implements Resolve<IRendicion> {
  constructor(protected service: RendicionService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IRendicion> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((rendicion: HttpResponse<Rendicion>) => {
          if (rendicion.body) {
            return of(rendicion.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Rendicion());
  }
}
