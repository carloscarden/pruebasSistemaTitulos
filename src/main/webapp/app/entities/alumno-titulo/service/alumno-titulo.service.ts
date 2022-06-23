import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IAlumnoTitulo, getAlumnoTituloIdentifier } from '../alumno-titulo.model';

export type EntityResponseType = HttpResponse<IAlumnoTitulo>;
export type EntityArrayResponseType = HttpResponse<IAlumnoTitulo[]>;

@Injectable({ providedIn: 'root' })
export class AlumnoTituloService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/alumno-titulos');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(alumnoTitulo: IAlumnoTitulo): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(alumnoTitulo);
    return this.http
      .post<IAlumnoTitulo>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(alumnoTitulo: IAlumnoTitulo): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(alumnoTitulo);
    return this.http
      .put<IAlumnoTitulo>(`${this.resourceUrl}/${getAlumnoTituloIdentifier(alumnoTitulo) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  partialUpdate(alumnoTitulo: IAlumnoTitulo): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(alumnoTitulo);
    return this.http
      .patch<IAlumnoTitulo>(`${this.resourceUrl}/${getAlumnoTituloIdentifier(alumnoTitulo) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IAlumnoTitulo>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IAlumnoTitulo[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addAlumnoTituloToCollectionIfMissing(
    alumnoTituloCollection: IAlumnoTitulo[],
    ...alumnoTitulosToCheck: (IAlumnoTitulo | null | undefined)[]
  ): IAlumnoTitulo[] {
    const alumnoTitulos: IAlumnoTitulo[] = alumnoTitulosToCheck.filter(isPresent);
    if (alumnoTitulos.length > 0) {
      const alumnoTituloCollectionIdentifiers = alumnoTituloCollection.map(
        alumnoTituloItem => getAlumnoTituloIdentifier(alumnoTituloItem)!
      );
      const alumnoTitulosToAdd = alumnoTitulos.filter(alumnoTituloItem => {
        const alumnoTituloIdentifier = getAlumnoTituloIdentifier(alumnoTituloItem);
        if (alumnoTituloIdentifier == null || alumnoTituloCollectionIdentifiers.includes(alumnoTituloIdentifier)) {
          return false;
        }
        alumnoTituloCollectionIdentifiers.push(alumnoTituloIdentifier);
        return true;
      });
      return [...alumnoTitulosToAdd, ...alumnoTituloCollection];
    }
    return alumnoTituloCollection;
  }

  protected convertDateFromClient(alumnoTitulo: IAlumnoTitulo): IAlumnoTitulo {
    return Object.assign({}, alumnoTitulo, {
      fechaEmision: alumnoTitulo.fechaEmision?.isValid() ? alumnoTitulo.fechaEmision.toJSON() : undefined,
      fechaEnvio: alumnoTitulo.fechaEnvio?.isValid() ? alumnoTitulo.fechaEnvio.toJSON() : undefined,
      fechaEgreso: alumnoTitulo.fechaEgreso?.isValid() ? alumnoTitulo.fechaEgreso.toJSON() : undefined,
      fechaRuta: alumnoTitulo.fechaRuta?.isValid() ? alumnoTitulo.fechaRuta.toJSON() : undefined,
      fechaUltimoEstado: alumnoTitulo.fechaUltimoEstado?.isValid() ? alumnoTitulo.fechaUltimoEstado.toJSON() : undefined,
    });
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.fechaEmision = res.body.fechaEmision ? dayjs(res.body.fechaEmision) : undefined;
      res.body.fechaEnvio = res.body.fechaEnvio ? dayjs(res.body.fechaEnvio) : undefined;
      res.body.fechaEgreso = res.body.fechaEgreso ? dayjs(res.body.fechaEgreso) : undefined;
      res.body.fechaRuta = res.body.fechaRuta ? dayjs(res.body.fechaRuta) : undefined;
      res.body.fechaUltimoEstado = res.body.fechaUltimoEstado ? dayjs(res.body.fechaUltimoEstado) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((alumnoTitulo: IAlumnoTitulo) => {
        alumnoTitulo.fechaEmision = alumnoTitulo.fechaEmision ? dayjs(alumnoTitulo.fechaEmision) : undefined;
        alumnoTitulo.fechaEnvio = alumnoTitulo.fechaEnvio ? dayjs(alumnoTitulo.fechaEnvio) : undefined;
        alumnoTitulo.fechaEgreso = alumnoTitulo.fechaEgreso ? dayjs(alumnoTitulo.fechaEgreso) : undefined;
        alumnoTitulo.fechaRuta = alumnoTitulo.fechaRuta ? dayjs(alumnoTitulo.fechaRuta) : undefined;
        alumnoTitulo.fechaUltimoEstado = alumnoTitulo.fechaUltimoEstado ? dayjs(alumnoTitulo.fechaUltimoEstado) : undefined;
      });
    }
    return res;
  }
}
