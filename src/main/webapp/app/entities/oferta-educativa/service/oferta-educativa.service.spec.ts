import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import dayjs from 'dayjs/esm';

import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IOfertaEducativa, OfertaEducativa } from '../oferta-educativa.model';

import { OfertaEducativaService } from './oferta-educativa.service';

describe('OfertaEducativa Service', () => {
  let service: OfertaEducativaService;
  let httpMock: HttpTestingController;
  let elemDefault: IOfertaEducativa;
  let expectedResult: IOfertaEducativa | IOfertaEducativa[] | boolean | null;
  let currentDate: dayjs.Dayjs;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(OfertaEducativaService);
    httpMock = TestBed.inject(HttpTestingController);
    currentDate = dayjs();

    elemDefault = {
      id: 0,
      idModalidad: 0,
      idNivel: 0,
      idOrientacion: 0,
      idTituloDenominacion: 0,
      observiaciones: 'AAAAAAA',
      idTipoTitulo: 0,
      idUser: 0,
      fechaInicio: currentDate,
      fechaFin: currentDate,
      fechaEstado: currentDate,
      idEstadoOferta: 0,
      idLeyEducacion: 0,
      idNormaAprobacionDen: 0,
      idNormaDictamenDen: 0,
      idSeCorrespondeCon: 0,
      titulo: 'AAAAAAA',
      tituloImpresion: 'AAAAAAA',
      tituloIntermedio: 'AAAAAAA',
      tituloIntermedioImpresion: 'AAAAAAA',
      orientacion: 'AAAAAAA',
      idRama: 0,
      idSeccionTituloIntermedio: 0,
      idCursoGrupoNombre: 0,
      correlatividad: 0,
      idModalidadDictado: 0,
      titula: 0,
      cicloBasico: 0,
    };
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = Object.assign(
        {
          fechaInicio: currentDate.format(DATE_TIME_FORMAT),
          fechaFin: currentDate.format(DATE_TIME_FORMAT),
          fechaEstado: currentDate.format(DATE_TIME_FORMAT),
        },
        elemDefault
      );

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(elemDefault);
    });

    it('should create a OfertaEducativa', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
          fechaInicio: currentDate.format(DATE_TIME_FORMAT),
          fechaFin: currentDate.format(DATE_TIME_FORMAT),
          fechaEstado: currentDate.format(DATE_TIME_FORMAT),
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          fechaInicio: currentDate,
          fechaFin: currentDate,
          fechaEstado: currentDate,
        },
        returnedFromService
      );

      service.create(new OfertaEducativa()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a OfertaEducativa', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          idModalidad: 1,
          idNivel: 1,
          idOrientacion: 1,
          idTituloDenominacion: 1,
          observiaciones: 'BBBBBB',
          idTipoTitulo: 1,
          idUser: 1,
          fechaInicio: currentDate.format(DATE_TIME_FORMAT),
          fechaFin: currentDate.format(DATE_TIME_FORMAT),
          fechaEstado: currentDate.format(DATE_TIME_FORMAT),
          idEstadoOferta: 1,
          idLeyEducacion: 1,
          idNormaAprobacionDen: 1,
          idNormaDictamenDen: 1,
          idSeCorrespondeCon: 1,
          titulo: 'BBBBBB',
          tituloImpresion: 'BBBBBB',
          tituloIntermedio: 'BBBBBB',
          tituloIntermedioImpresion: 'BBBBBB',
          orientacion: 'BBBBBB',
          idRama: 1,
          idSeccionTituloIntermedio: 1,
          idCursoGrupoNombre: 1,
          correlatividad: 1,
          idModalidadDictado: 1,
          titula: 1,
          cicloBasico: 1,
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          fechaInicio: currentDate,
          fechaFin: currentDate,
          fechaEstado: currentDate,
        },
        returnedFromService
      );

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a OfertaEducativa', () => {
      const patchObject = Object.assign(
        {
          idNivel: 1,
          idOrientacion: 1,
          idTituloDenominacion: 1,
          observiaciones: 'BBBBBB',
          fechaInicio: currentDate.format(DATE_TIME_FORMAT),
          fechaEstado: currentDate.format(DATE_TIME_FORMAT),
          idEstadoOferta: 1,
          idLeyEducacion: 1,
          idNormaAprobacionDen: 1,
          titulo: 'BBBBBB',
          tituloIntermedio: 'BBBBBB',
          tituloIntermedioImpresion: 'BBBBBB',
          orientacion: 'BBBBBB',
          titula: 1,
        },
        new OfertaEducativa()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign(
        {
          fechaInicio: currentDate,
          fechaFin: currentDate,
          fechaEstado: currentDate,
        },
        returnedFromService
      );

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of OfertaEducativa', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          idModalidad: 1,
          idNivel: 1,
          idOrientacion: 1,
          idTituloDenominacion: 1,
          observiaciones: 'BBBBBB',
          idTipoTitulo: 1,
          idUser: 1,
          fechaInicio: currentDate.format(DATE_TIME_FORMAT),
          fechaFin: currentDate.format(DATE_TIME_FORMAT),
          fechaEstado: currentDate.format(DATE_TIME_FORMAT),
          idEstadoOferta: 1,
          idLeyEducacion: 1,
          idNormaAprobacionDen: 1,
          idNormaDictamenDen: 1,
          idSeCorrespondeCon: 1,
          titulo: 'BBBBBB',
          tituloImpresion: 'BBBBBB',
          tituloIntermedio: 'BBBBBB',
          tituloIntermedioImpresion: 'BBBBBB',
          orientacion: 'BBBBBB',
          idRama: 1,
          idSeccionTituloIntermedio: 1,
          idCursoGrupoNombre: 1,
          correlatividad: 1,
          idModalidadDictado: 1,
          titula: 1,
          cicloBasico: 1,
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          fechaInicio: currentDate,
          fechaFin: currentDate,
          fechaEstado: currentDate,
        },
        returnedFromService
      );

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toContainEqual(expected);
    });

    it('should delete a OfertaEducativa', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addOfertaEducativaToCollectionIfMissing', () => {
      it('should add a OfertaEducativa to an empty array', () => {
        const ofertaEducativa: IOfertaEducativa = { id: 123 };
        expectedResult = service.addOfertaEducativaToCollectionIfMissing([], ofertaEducativa);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(ofertaEducativa);
      });

      it('should not add a OfertaEducativa to an array that contains it', () => {
        const ofertaEducativa: IOfertaEducativa = { id: 123 };
        const ofertaEducativaCollection: IOfertaEducativa[] = [
          {
            ...ofertaEducativa,
          },
          { id: 456 },
        ];
        expectedResult = service.addOfertaEducativaToCollectionIfMissing(ofertaEducativaCollection, ofertaEducativa);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a OfertaEducativa to an array that doesn't contain it", () => {
        const ofertaEducativa: IOfertaEducativa = { id: 123 };
        const ofertaEducativaCollection: IOfertaEducativa[] = [{ id: 456 }];
        expectedResult = service.addOfertaEducativaToCollectionIfMissing(ofertaEducativaCollection, ofertaEducativa);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(ofertaEducativa);
      });

      it('should add only unique OfertaEducativa to an array', () => {
        const ofertaEducativaArray: IOfertaEducativa[] = [{ id: 123 }, { id: 456 }, { id: 62984 }];
        const ofertaEducativaCollection: IOfertaEducativa[] = [{ id: 123 }];
        expectedResult = service.addOfertaEducativaToCollectionIfMissing(ofertaEducativaCollection, ...ofertaEducativaArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const ofertaEducativa: IOfertaEducativa = { id: 123 };
        const ofertaEducativa2: IOfertaEducativa = { id: 456 };
        expectedResult = service.addOfertaEducativaToCollectionIfMissing([], ofertaEducativa, ofertaEducativa2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(ofertaEducativa);
        expect(expectedResult).toContain(ofertaEducativa2);
      });

      it('should accept null and undefined values', () => {
        const ofertaEducativa: IOfertaEducativa = { id: 123 };
        expectedResult = service.addOfertaEducativaToCollectionIfMissing([], null, ofertaEducativa, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(ofertaEducativa);
      });

      it('should return initial array if no OfertaEducativa is added', () => {
        const ofertaEducativaCollection: IOfertaEducativa[] = [{ id: 123 }];
        expectedResult = service.addOfertaEducativaToCollectionIfMissing(ofertaEducativaCollection, undefined, null);
        expect(expectedResult).toEqual(ofertaEducativaCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
