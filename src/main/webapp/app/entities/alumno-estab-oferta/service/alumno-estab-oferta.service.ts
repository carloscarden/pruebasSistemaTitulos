import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IAlumnoEstabOferta, getAlumnoEstabOfertaIdentifier } from '../alumno-estab-oferta.model';

export type EntityResponseType = HttpResponse<IAlumnoEstabOferta>;
export type EntityArrayResponseType = HttpResponse<IAlumnoEstabOferta[]>;

@Injectable({ providedIn: 'root' })
export class AlumnoEstabOfertaService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/alumno-estab-ofertas');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(alumnoEstabOferta: IAlumnoEstabOferta): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(alumnoEstabOferta);
    return this.http
      .post<IAlumnoEstabOferta>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(alumnoEstabOferta: IAlumnoEstabOferta): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(alumnoEstabOferta);
    return this.http
      .put<IAlumnoEstabOferta>(`${this.resourceUrl}/${getAlumnoEstabOfertaIdentifier(alumnoEstabOferta) as number}`, copy, {
        observe: 'response',
      })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  partialUpdate(alumnoEstabOferta: IAlumnoEstabOferta): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(alumnoEstabOferta);
    return this.http
      .patch<IAlumnoEstabOferta>(`${this.resourceUrl}/${getAlumnoEstabOfertaIdentifier(alumnoEstabOferta) as number}`, copy, {
        observe: 'response',
      })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IAlumnoEstabOferta>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IAlumnoEstabOferta[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addAlumnoEstabOfertaToCollectionIfMissing(
    alumnoEstabOfertaCollection: IAlumnoEstabOferta[],
    ...alumnoEstabOfertasToCheck: (IAlumnoEstabOferta | null | undefined)[]
  ): IAlumnoEstabOferta[] {
    const alumnoEstabOfertas: IAlumnoEstabOferta[] = alumnoEstabOfertasToCheck.filter(isPresent);
    if (alumnoEstabOfertas.length > 0) {
      const alumnoEstabOfertaCollectionIdentifiers = alumnoEstabOfertaCollection.map(
        alumnoEstabOfertaItem => getAlumnoEstabOfertaIdentifier(alumnoEstabOfertaItem)!
      );
      const alumnoEstabOfertasToAdd = alumnoEstabOfertas.filter(alumnoEstabOfertaItem => {
        const alumnoEstabOfertaIdentifier = getAlumnoEstabOfertaIdentifier(alumnoEstabOfertaItem);
        if (alumnoEstabOfertaIdentifier == null || alumnoEstabOfertaCollectionIdentifiers.includes(alumnoEstabOfertaIdentifier)) {
          return false;
        }
        alumnoEstabOfertaCollectionIdentifiers.push(alumnoEstabOfertaIdentifier);
        return true;
      });
      return [...alumnoEstabOfertasToAdd, ...alumnoEstabOfertaCollection];
    }
    return alumnoEstabOfertaCollection;
  }

  protected convertDateFromClient(alumnoEstabOferta: IAlumnoEstabOferta): IAlumnoEstabOferta {
    return Object.assign({}, alumnoEstabOferta, {
      fechaInicio: alumnoEstabOferta.fechaInicio?.isValid() ? alumnoEstabOferta.fechaInicio.toJSON() : undefined,
      fechaFin: alumnoEstabOferta.fechaFin?.isValid() ? alumnoEstabOferta.fechaFin.toJSON() : undefined,
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
      res.body.forEach((alumnoEstabOferta: IAlumnoEstabOferta) => {
        alumnoEstabOferta.fechaInicio = alumnoEstabOferta.fechaInicio ? dayjs(alumnoEstabOferta.fechaInicio) : undefined;
        alumnoEstabOferta.fechaFin = alumnoEstabOferta.fechaFin ? dayjs(alumnoEstabOferta.fechaFin) : undefined;
      });
    }
    return res;
  }
}
