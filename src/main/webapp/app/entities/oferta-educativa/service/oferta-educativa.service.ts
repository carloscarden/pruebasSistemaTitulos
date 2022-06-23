import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IOfertaEducativa, getOfertaEducativaIdentifier } from '../oferta-educativa.model';

export type EntityResponseType = HttpResponse<IOfertaEducativa>;
export type EntityArrayResponseType = HttpResponse<IOfertaEducativa[]>;

@Injectable({ providedIn: 'root' })
export class OfertaEducativaService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/oferta-educativas');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(ofertaEducativa: IOfertaEducativa): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(ofertaEducativa);
    return this.http
      .post<IOfertaEducativa>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(ofertaEducativa: IOfertaEducativa): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(ofertaEducativa);
    return this.http
      .put<IOfertaEducativa>(`${this.resourceUrl}/${getOfertaEducativaIdentifier(ofertaEducativa) as number}`, copy, {
        observe: 'response',
      })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  partialUpdate(ofertaEducativa: IOfertaEducativa): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(ofertaEducativa);
    return this.http
      .patch<IOfertaEducativa>(`${this.resourceUrl}/${getOfertaEducativaIdentifier(ofertaEducativa) as number}`, copy, {
        observe: 'response',
      })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IOfertaEducativa>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IOfertaEducativa[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addOfertaEducativaToCollectionIfMissing(
    ofertaEducativaCollection: IOfertaEducativa[],
    ...ofertaEducativasToCheck: (IOfertaEducativa | null | undefined)[]
  ): IOfertaEducativa[] {
    const ofertaEducativas: IOfertaEducativa[] = ofertaEducativasToCheck.filter(isPresent);
    if (ofertaEducativas.length > 0) {
      const ofertaEducativaCollectionIdentifiers = ofertaEducativaCollection.map(
        ofertaEducativaItem => getOfertaEducativaIdentifier(ofertaEducativaItem)!
      );
      const ofertaEducativasToAdd = ofertaEducativas.filter(ofertaEducativaItem => {
        const ofertaEducativaIdentifier = getOfertaEducativaIdentifier(ofertaEducativaItem);
        if (ofertaEducativaIdentifier == null || ofertaEducativaCollectionIdentifiers.includes(ofertaEducativaIdentifier)) {
          return false;
        }
        ofertaEducativaCollectionIdentifiers.push(ofertaEducativaIdentifier);
        return true;
      });
      return [...ofertaEducativasToAdd, ...ofertaEducativaCollection];
    }
    return ofertaEducativaCollection;
  }

  protected convertDateFromClient(ofertaEducativa: IOfertaEducativa): IOfertaEducativa {
    return Object.assign({}, ofertaEducativa, {
      fechaInicio: ofertaEducativa.fechaInicio?.isValid() ? ofertaEducativa.fechaInicio.toJSON() : undefined,
      fechaFin: ofertaEducativa.fechaFin?.isValid() ? ofertaEducativa.fechaFin.toJSON() : undefined,
      fechaEstado: ofertaEducativa.fechaEstado?.isValid() ? ofertaEducativa.fechaEstado.toJSON() : undefined,
    });
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.fechaInicio = res.body.fechaInicio ? dayjs(res.body.fechaInicio) : undefined;
      res.body.fechaFin = res.body.fechaFin ? dayjs(res.body.fechaFin) : undefined;
      res.body.fechaEstado = res.body.fechaEstado ? dayjs(res.body.fechaEstado) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((ofertaEducativa: IOfertaEducativa) => {
        ofertaEducativa.fechaInicio = ofertaEducativa.fechaInicio ? dayjs(ofertaEducativa.fechaInicio) : undefined;
        ofertaEducativa.fechaFin = ofertaEducativa.fechaFin ? dayjs(ofertaEducativa.fechaFin) : undefined;
        ofertaEducativa.fechaEstado = ofertaEducativa.fechaEstado ? dayjs(ofertaEducativa.fechaEstado) : undefined;
      });
    }
    return res;
  }
}
