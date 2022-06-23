import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IRendicion, getRendicionIdentifier } from '../rendicion.model';

export type EntityResponseType = HttpResponse<IRendicion>;
export type EntityArrayResponseType = HttpResponse<IRendicion[]>;

@Injectable({ providedIn: 'root' })
export class RendicionService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/rendicions');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(rendicion: IRendicion): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(rendicion);
    return this.http
      .post<IRendicion>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(rendicion: IRendicion): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(rendicion);
    return this.http
      .put<IRendicion>(`${this.resourceUrl}/${getRendicionIdentifier(rendicion) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  partialUpdate(rendicion: IRendicion): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(rendicion);
    return this.http
      .patch<IRendicion>(`${this.resourceUrl}/${getRendicionIdentifier(rendicion) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IRendicion>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IRendicion[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addRendicionToCollectionIfMissing(
    rendicionCollection: IRendicion[],
    ...rendicionsToCheck: (IRendicion | null | undefined)[]
  ): IRendicion[] {
    const rendicions: IRendicion[] = rendicionsToCheck.filter(isPresent);
    if (rendicions.length > 0) {
      const rendicionCollectionIdentifiers = rendicionCollection.map(rendicionItem => getRendicionIdentifier(rendicionItem)!);
      const rendicionsToAdd = rendicions.filter(rendicionItem => {
        const rendicionIdentifier = getRendicionIdentifier(rendicionItem);
        if (rendicionIdentifier == null || rendicionCollectionIdentifiers.includes(rendicionIdentifier)) {
          return false;
        }
        rendicionCollectionIdentifiers.push(rendicionIdentifier);
        return true;
      });
      return [...rendicionsToAdd, ...rendicionCollection];
    }
    return rendicionCollection;
  }

  protected convertDateFromClient(rendicion: IRendicion): IRendicion {
    return Object.assign({}, rendicion, {
      fechaAnulacion: rendicion.fechaAnulacion?.isValid() ? rendicion.fechaAnulacion.format(DATE_FORMAT) : undefined,
    });
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.fechaAnulacion = res.body.fechaAnulacion ? dayjs(res.body.fechaAnulacion) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((rendicion: IRendicion) => {
        rendicion.fechaAnulacion = rendicion.fechaAnulacion ? dayjs(rendicion.fechaAnulacion) : undefined;
      });
    }
    return res;
  }
}
