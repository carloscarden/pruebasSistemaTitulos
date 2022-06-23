import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IAlumnoAnalitico, getAlumnoAnaliticoIdentifier } from '../alumno-analitico.model';

export type EntityResponseType = HttpResponse<IAlumnoAnalitico>;
export type EntityArrayResponseType = HttpResponse<IAlumnoAnalitico[]>;

@Injectable({ providedIn: 'root' })
export class AlumnoAnaliticoService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/alumno-analiticos');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(alumnoAnalitico: IAlumnoAnalitico): Observable<EntityResponseType> {
    return this.http.post<IAlumnoAnalitico>(this.resourceUrl, alumnoAnalitico, { observe: 'response' });
  }

  update(alumnoAnalitico: IAlumnoAnalitico): Observable<EntityResponseType> {
    return this.http.put<IAlumnoAnalitico>(
      `${this.resourceUrl}/${getAlumnoAnaliticoIdentifier(alumnoAnalitico) as number}`,
      alumnoAnalitico,
      { observe: 'response' }
    );
  }

  partialUpdate(alumnoAnalitico: IAlumnoAnalitico): Observable<EntityResponseType> {
    return this.http.patch<IAlumnoAnalitico>(
      `${this.resourceUrl}/${getAlumnoAnaliticoIdentifier(alumnoAnalitico) as number}`,
      alumnoAnalitico,
      { observe: 'response' }
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IAlumnoAnalitico>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAlumnoAnalitico[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addAlumnoAnaliticoToCollectionIfMissing(
    alumnoAnaliticoCollection: IAlumnoAnalitico[],
    ...alumnoAnaliticosToCheck: (IAlumnoAnalitico | null | undefined)[]
  ): IAlumnoAnalitico[] {
    const alumnoAnaliticos: IAlumnoAnalitico[] = alumnoAnaliticosToCheck.filter(isPresent);
    if (alumnoAnaliticos.length > 0) {
      const alumnoAnaliticoCollectionIdentifiers = alumnoAnaliticoCollection.map(
        alumnoAnaliticoItem => getAlumnoAnaliticoIdentifier(alumnoAnaliticoItem)!
      );
      const alumnoAnaliticosToAdd = alumnoAnaliticos.filter(alumnoAnaliticoItem => {
        const alumnoAnaliticoIdentifier = getAlumnoAnaliticoIdentifier(alumnoAnaliticoItem);
        if (alumnoAnaliticoIdentifier == null || alumnoAnaliticoCollectionIdentifiers.includes(alumnoAnaliticoIdentifier)) {
          return false;
        }
        alumnoAnaliticoCollectionIdentifiers.push(alumnoAnaliticoIdentifier);
        return true;
      });
      return [...alumnoAnaliticosToAdd, ...alumnoAnaliticoCollection];
    }
    return alumnoAnaliticoCollection;
  }
}
