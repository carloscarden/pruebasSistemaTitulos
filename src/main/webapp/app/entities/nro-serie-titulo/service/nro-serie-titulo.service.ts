import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { INroSerieTitulo, getNroSerieTituloIdentifier } from '../nro-serie-titulo.model';

export type EntityResponseType = HttpResponse<INroSerieTitulo>;
export type EntityArrayResponseType = HttpResponse<INroSerieTitulo[]>;

@Injectable({ providedIn: 'root' })
export class NroSerieTituloService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/nro-serie-titulos');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(nroSerieTitulo: INroSerieTitulo): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(nroSerieTitulo);
    return this.http
      .post<INroSerieTitulo>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(nroSerieTitulo: INroSerieTitulo): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(nroSerieTitulo);
    return this.http
      .put<INroSerieTitulo>(`${this.resourceUrl}/${getNroSerieTituloIdentifier(nroSerieTitulo) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  partialUpdate(nroSerieTitulo: INroSerieTitulo): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(nroSerieTitulo);
    return this.http
      .patch<INroSerieTitulo>(`${this.resourceUrl}/${getNroSerieTituloIdentifier(nroSerieTitulo) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<INroSerieTitulo>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<INroSerieTitulo[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addNroSerieTituloToCollectionIfMissing(
    nroSerieTituloCollection: INroSerieTitulo[],
    ...nroSerieTitulosToCheck: (INroSerieTitulo | null | undefined)[]
  ): INroSerieTitulo[] {
    const nroSerieTitulos: INroSerieTitulo[] = nroSerieTitulosToCheck.filter(isPresent);
    if (nroSerieTitulos.length > 0) {
      const nroSerieTituloCollectionIdentifiers = nroSerieTituloCollection.map(
        nroSerieTituloItem => getNroSerieTituloIdentifier(nroSerieTituloItem)!
      );
      const nroSerieTitulosToAdd = nroSerieTitulos.filter(nroSerieTituloItem => {
        const nroSerieTituloIdentifier = getNroSerieTituloIdentifier(nroSerieTituloItem);
        if (nroSerieTituloIdentifier == null || nroSerieTituloCollectionIdentifiers.includes(nroSerieTituloIdentifier)) {
          return false;
        }
        nroSerieTituloCollectionIdentifiers.push(nroSerieTituloIdentifier);
        return true;
      });
      return [...nroSerieTitulosToAdd, ...nroSerieTituloCollection];
    }
    return nroSerieTituloCollection;
  }

  protected convertDateFromClient(nroSerieTitulo: INroSerieTitulo): INroSerieTitulo {
    return Object.assign({}, nroSerieTitulo, {
      fechaInicio: nroSerieTitulo.fechaInicio?.isValid() ? nroSerieTitulo.fechaInicio.format(DATE_FORMAT) : undefined,
      fechaFin: nroSerieTitulo.fechaFin?.isValid() ? nroSerieTitulo.fechaFin.format(DATE_FORMAT) : undefined,
    });
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.fechaInicio = res.body.fechaInicio ? dayjs(res.body.fechaInicio) : undefined;
      res.body.fechaFin = res.body.fechaFin ? dayjs(res.body.fechaFin) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((nroSerieTitulo: INroSerieTitulo) => {
        nroSerieTitulo.fechaInicio = nroSerieTitulo.fechaInicio ? dayjs(nroSerieTitulo.fechaInicio) : undefined;
        nroSerieTitulo.fechaFin = nroSerieTitulo.fechaFin ? dayjs(nroSerieTitulo.fechaFin) : undefined;
      });
    }
    return res;
  }
}
