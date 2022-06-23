import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITurno, getTurnoIdentifier } from '../turno.model';

export type EntityResponseType = HttpResponse<ITurno>;
export type EntityArrayResponseType = HttpResponse<ITurno[]>;

@Injectable({ providedIn: 'root' })
export class TurnoService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/turnos');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITurno>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITurno[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  addTurnoToCollectionIfMissing(turnoCollection: ITurno[], ...turnosToCheck: (ITurno | null | undefined)[]): ITurno[] {
    const turnos: ITurno[] = turnosToCheck.filter(isPresent);
    if (turnos.length > 0) {
      const turnoCollectionIdentifiers = turnoCollection.map(turnoItem => getTurnoIdentifier(turnoItem)!);
      const turnosToAdd = turnos.filter(turnoItem => {
        const turnoIdentifier = getTurnoIdentifier(turnoItem);
        if (turnoIdentifier == null || turnoCollectionIdentifiers.includes(turnoIdentifier)) {
          return false;
        }
        turnoCollectionIdentifiers.push(turnoIdentifier);
        return true;
      });
      return [...turnosToAdd, ...turnoCollection];
    }
    return turnoCollection;
  }

  protected convertDateFromClient(turno: ITurno): ITurno {
    return Object.assign({}, turno, {
      descripcion: turno.descripcion?.isValid() ? turno.descripcion.format(DATE_FORMAT) : undefined,
      vigh: turno.vigh?.isValid() ? turno.vigh.format(DATE_FORMAT) : undefined,
    });
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.descripcion = res.body.descripcion ? dayjs(res.body.descripcion) : undefined;
      res.body.vigh = res.body.vigh ? dayjs(res.body.vigh) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((turno: ITurno) => {
        turno.descripcion = turno.descripcion ? dayjs(turno.descripcion) : undefined;
        turno.vigh = turno.vigh ? dayjs(turno.vigh) : undefined;
      });
    }
    return res;
  }
}
