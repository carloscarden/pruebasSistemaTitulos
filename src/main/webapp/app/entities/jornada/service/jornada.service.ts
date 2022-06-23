import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IJornada, getJornadaIdentifier } from '../jornada.model';

export type EntityResponseType = HttpResponse<IJornada>;
export type EntityArrayResponseType = HttpResponse<IJornada[]>;

@Injectable({ providedIn: 'root' })
export class JornadaService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/jornadas');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IJornada>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IJornada[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  addJornadaToCollectionIfMissing(jornadaCollection: IJornada[], ...jornadasToCheck: (IJornada | null | undefined)[]): IJornada[] {
    const jornadas: IJornada[] = jornadasToCheck.filter(isPresent);
    if (jornadas.length > 0) {
      const jornadaCollectionIdentifiers = jornadaCollection.map(jornadaItem => getJornadaIdentifier(jornadaItem)!);
      const jornadasToAdd = jornadas.filter(jornadaItem => {
        const jornadaIdentifier = getJornadaIdentifier(jornadaItem);
        if (jornadaIdentifier == null || jornadaCollectionIdentifiers.includes(jornadaIdentifier)) {
          return false;
        }
        jornadaCollectionIdentifiers.push(jornadaIdentifier);
        return true;
      });
      return [...jornadasToAdd, ...jornadaCollection];
    }
    return jornadaCollection;
  }
}
